package com.comida.sia.indicators.adapter.acquirer.treasuryyeald;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.indicators.domain.model.treasuryyeald.Interval;
import com.comida.sia.indicators.domain.model.treasuryyeald.Maturity;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdData;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdDataAcquirer;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdEntry;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.messaging.GeneralSerializer;
import com.comida.sia.sharedkernel.period.PeriodType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageTreasuryYealdDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageTreasuryYealdDataAcquirer implements TreasuryYealdDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&interval={interval}&maturity={maturity}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "TREASURY_YIELD";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<TreasuryYealdData> serializer = new GeneralSerializer<>(true, true);

	@Override
	public TreasuryYealdData gatherGdpGeneral(Interval interval, Maturity maturity) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(interval, maturity);
		TreasuryYealdData treasuryYealdData = null;
		
		if(cacheFileManager.canReadCashe()) {
			treasuryYealdData = serializer.deserialize(cacheFileManager.readFile(), TreasuryYealdData.class);
			
			log.info("Treasury yeald for " + interval + " interval and " + maturity + " maturity read from cashe");
		} else {
			cacheFileManager.deleteFile();
			treasuryYealdData = gatherFromAcquirer(interval, maturity);
			cacheFileManager.writeFile(serializer.serialize(treasuryYealdData));
			
			log.info("Treasury yeald for " + interval + " interval and " + maturity + " maturity read from alfavantage");
		}
		
		return treasuryYealdData;
	}
	
	private TreasuryYealdData gatherFromAcquirer(Interval interval, Maturity maturity) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("interval", interval.getName());
		params.put("maturity", maturity.getName());
		params.put("functionName", FUNCTION_NAME);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		TreasuryYealdData treasuryYealdData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(TreasuryYealdData.class);	
		
		enreachEntryData(treasuryYealdData, interval, maturity);
		
		return treasuryYealdData;
	}
	
	private void enreachEntryData(TreasuryYealdData treasuryYealdData, Interval interval, Maturity maturity) {
		for(TreasuryYealdEntry entry : treasuryYealdData.getData()) {
			entry.setInterval(interval);
			entry.setMaturity(maturity);
		}
	}

	private CacheFileManager getCacheFileManager(Interval interval, Maturity maturity) {
		String relativePath = "indicators\\treasury yeald\\";
		String fileName = "us_treasury_yeald_" + interval.name() + "_" + maturity.name();
		
		CacheFileManager cacheFileManager = new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
		
		return cacheFileManager;
	}
}
