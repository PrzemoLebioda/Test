package com.comida.sia.indicators.adapter.acquirer.gdp;

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
@Component("AlfavantageGdpPerCapitaDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageGdpPerCapitaDataAcquirer implements IndicatorsDataAcquirer{

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&interval={interval}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "REAL_GDP_PER_CAPITA";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<IndicatorsData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public IndicatorsData gatherIndicatorData(Interval interval) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(interval);
		IndicatorsData grossDomesticProductData = null;
		
		if(cacheFileManager.canReadCashe()) {
			grossDomesticProductData = serializer.deserialize(cacheFileManager.readFile(), IndicatorsData.class);
			
			log.info("Gross Domestic Product per capitafor " + interval + " interval read from cashe");
		} else {
			cacheFileManager.deleteFile();
			grossDomesticProductData = gatherFromAcquirer(interval);
			cacheFileManager.writeFile(serializer.serialize(grossDomesticProductData));
			
			log.info("Gross Domestic Product per capitafor " + interval + " interval read from alfavantage");
		}
		
		return grossDomesticProductData;
	}
	
	private IndicatorsData gatherFromAcquirer(Interval interval) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("interval", interval.getName());
		params.put("functionName", FUNCTION_NAME);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		IndicatorsData grossDomesticProductData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(IndicatorsData.class);	
		
		enreachEntryData(grossDomesticProductData, interval);
		
		return grossDomesticProductData;
	}
	
	private void enreachEntryData(IndicatorsData grossDomesticProductData, Interval interval) {
		for(IndicatorsDataEntry entry : grossDomesticProductData.getData()) {
			entry.setInterval(interval);
		}
	}

	private CacheFileManager getCacheFileManager(Interval interval) {
		String relativePath = "indicators\\gdp\\";
		String fileName = interval.name() + "_us_gdp_per_capita";
		
		CacheFileManager cacheFileManager = new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
		
		switch(interval) {
			case ANNUAL:
				return cacheFileManager;
			case QUARTERLY:
				return cacheFileManager;
			default:
				throw new IllegalArgumentException("Not supported interval type");
		}
	}

}
