package com.comida.sia.indicators.adapter.acquirer.cpi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.port.acquirer.IndicatorsData;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.messaging.GeneralSerializer;
import com.comida.sia.sharedkernel.period.PeriodType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageCpiDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageCpiDataAcquirer implements IndicatorsDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&interval={interval}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "CPI";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<IndicatorsData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public IndicatorsData gatherIndicatorData(Interval interval) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(interval);
		IndicatorsData consumerPriceIndex = null;
		
		if(cacheFileManager.canReadCashe()) {
			consumerPriceIndex = serializer.deserialize(cacheFileManager.readFile(), IndicatorsData.class);
			
			log.info("Consumer Price Index for " + interval + " interval read from cashe");
		} else {
			cacheFileManager.deleteFile();
			consumerPriceIndex = gatherFromAcquirer(interval);
			cacheFileManager.writeFile(serializer.serialize(consumerPriceIndex));
		
			log.info("Consumer Price Index for " + interval + " interval read from alfavantage");
		}
		
		return consumerPriceIndex;
	}
	
	private IndicatorsData gatherFromAcquirer(Interval interval) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("interval", interval.getName());
		params.put("functionName", FUNCTION_NAME);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		IndicatorsData consumerPriceIndexData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(IndicatorsData.class);	
		
		enreachEntryData(consumerPriceIndexData, interval.getName());
		
		return consumerPriceIndexData;
	}
	
	private void enreachEntryData(IndicatorsData consumerPriceIndexData, String interval) {
		for(IndicatorsDataEntry entry : consumerPriceIndexData.getData()) {
			entry.setInterval(Interval.get(interval));
		}
	}

	private CacheFileManager getCacheFileManager(Interval interval) {
		String relativePath = "indicators\\cpi\\";
		String fileName = interval.name() + "_us_cpi";
		
		CacheFileManager cacheFileManager = new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
		
		switch(interval) {
			case SEMIANNUAL:
				return cacheFileManager;
			case MONTHLY:
				return cacheFileManager;
			default:
				throw new IllegalArgumentException("Not supported interval type");
		}
	}

}
