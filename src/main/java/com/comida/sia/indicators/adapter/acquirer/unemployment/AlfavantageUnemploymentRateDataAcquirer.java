package com.comida.sia.indicators.adapter.acquirer.unemployment;

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
@Component("AlfavantageUnemploymentRateDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageUnemploymentRateDataAcquirer implements IndicatorsDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "UNEMPLOYMENT";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<IndicatorsData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public IndicatorsData gatherIndicatorData(Interval interval) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(interval);
		IndicatorsData unemploymentRateData = null;
		
		if(cacheFileManager.canReadCashe()) {
			unemploymentRateData = serializer.deserialize(cacheFileManager.readFile(), IndicatorsData.class);
			
			log.info("Unemployment Rate for " + interval + " interval read from cashe");
		} else {
			cacheFileManager.deleteFile();
			unemploymentRateData = gatherFromAcquirer(interval);
			cacheFileManager.writeFile(serializer.serialize(unemploymentRateData));
			
			log.info("Unemployment Rate for " + interval + " interval read from alfavantage");
		}
		
		return unemploymentRateData;
	}
	
	private IndicatorsData gatherFromAcquirer(Interval interval) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("interval", interval.getName());
		params.put("functionName", FUNCTION_NAME);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		IndicatorsData unemploymentRateData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(IndicatorsData.class);	
		
		enreachEntryData(unemploymentRateData, interval.getName());
		
		return unemploymentRateData;
	}
	
	private void enreachEntryData(IndicatorsData unemploymentRateData, String interval) {
		for(IndicatorsDataEntry entry : unemploymentRateData.getData()) {
			entry.setInterval(Interval.get(interval));
		}
	}

	private CacheFileManager getCacheFileManager(Interval interval) {
		String relativePath = "indicators\\unemployment rate\\";
		String fileName = interval.name() + "_us_unemployment_rate";
		
		CacheFileManager cacheFileManager = new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
		
		switch(interval) {
			case MONTHLY:
				return cacheFileManager;
			default:
				throw new IllegalArgumentException("Not supported interval type");
		}
	}

}
