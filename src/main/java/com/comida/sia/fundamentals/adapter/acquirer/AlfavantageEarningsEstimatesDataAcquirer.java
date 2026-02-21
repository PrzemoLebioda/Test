package com.comida.sia.fundamentals.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningEstimatesReportData;
import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningsEstimatesData;
import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningsEstimatesDataAcquirer;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheDataNotExistsException;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.CacheWaterMarkLevel;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sharedkernel.messaging.GeneralSerializer;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageEarningsEstimatesDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageEarningsEstimatesDataAcquirer implements EarningsEstimatesDataAcquirer{

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "EARNINGS_ESTIMATES";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<EarningsEstimatesData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public EarningsEstimatesData gatherEarningsEstimatesDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		EarningsEstimatesData earningsEstimatesData = null;
		
		if(cacheFileManager.canReadCashe()) {
			earningsEstimatesData = serializer.deserialize(cacheFileManager.readFile(), EarningsEstimatesData.class);
			
			log.info("Earnings estimates for " + tickerSymbol + " gathered from cashe");
		} else {
			cacheFileManager.deleteFile();
			earningsEstimatesData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(earningsEstimatesData));
			
			log.info("Earnings estimates for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return earningsEstimatesData;
	}
	
	@Override
	public EarningsEstimatesData gatherEarningsEstimatesDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case EARNING_ESTIMATES_REPORT:
				return gatherEarningsEstimatesData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}

	private EarningsEstimatesData gatherEarningsEstimatesData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache cache = getFundamentalsCacheAssembler(tickerSymbol).assemblyEarningsEstimatesCache();
		
		EarningsEstimatesData earningsEstimatesData;
		
		try {
			earningsEstimatesData = serializer
					.deserialize(
							cache.read(new CacheWaterMarkLevel(syncState)), 
							EarningsEstimatesData.class
					);
			
			log.info("Earnings estimates for " + tickerSymbol + " gathered from cashe");
			
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			earningsEstimatesData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(earningsEstimatesData.getEstimates());
			
			cache.update(
					earningsEstimatesData.getEstimates().get(earningsEstimatesData.getEstimates().size() - 1),
					serializer.serialize(earningsEstimatesData)
				);
			
			log.info("Earnings estimates for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return earningsEstimatesData;
	}

	private EarningsEstimatesData gatherFromAcquirer(String tickerSymbol) {
		EarningsEstimatesData earningsEstimatesData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();
			
			earningsEstimatesData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(EarningsEstimatesData.class);

			enrichReportsData(earningsEstimatesData);
			
			return earningsEstimatesData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			earningsEstimatesData = new EarningsEstimatesData();
			earningsEstimatesData.setSymbol(tickerSymbol);
			
			return earningsEstimatesData;
		}
	}
	
	private void enrichReportsData(EarningsEstimatesData earningsEstimatesData) {
		enrich(earningsEstimatesData.getEstimates(), earningsEstimatesData.getSymbol());
	}
	
	private void enrich(List<EarningEstimatesReportData> reports, String tickerSymbol) {
		for(EarningEstimatesReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "earning_estimates";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}

	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
