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

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsData;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsDataAcquirer;
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
@Component("AlfavantageEarningsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageEarningsDataAcquirer implements EarningsDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "EARNINGS";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<EarningsData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public EarningsData gatherEarningsDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		EarningsData earningsData = null;
		
		if(cacheFileManager.canReadCashe()) {
			earningsData = serializer.deserialize(cacheFileManager.readFile(), EarningsData.class);

			log.info("Earnings report for " + tickerSymbol + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			earningsData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(earningsData));

			log.info("Earnings report for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return earningsData;
		
	}
	
	@Override
	public EarningsData gatherEarningsDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case EARNINGS_ANNUAL_REPORT:
				return gatherAnnualEarningsData(tickerSymbol, syncState);
			case EARNINGS_QUARTER_REPORT:
				return gatherQuarterlyEarningsData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private EarningsData gatherAnnualEarningsData(String tickerSymbol, SynchronizationStateDto syncState) throws IOException, ObsoleteCashFileException, LackOfNewDataException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyEarningsCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualEarningsCache();
		
		EarningsData earningsData;
		
		try {
			earningsData = serializer
					.deserialize(
							annualCache.read(new CacheWaterMarkLevel(syncState)), 
							EarningsData.class
					);
			
			log.info("Annual earnings report for " + tickerSymbol + " gathered from cache");
		
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			earningsData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(earningsData.getAnnualEarnings());
			Collections.sort(earningsData.getQuarterlyEarnings());
			
			annualCache.update(
					earningsData.getAnnualEarnings().get(earningsData.getAnnualEarnings().size() - 1),
					serializer.serialize(earningsData));
			
			quoterlyCache.update(earningsData.getQuarterlyEarnings().get(earningsData.getQuarterlyEarnings().size() - 1));
			
			log.info("Annual earnings report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return earningsData;
	}

	private EarningsData gatherQuarterlyEarningsData(String tickerSymbol, SynchronizationStateDto syncState) throws IOException, ObsoleteCashFileException, LackOfNewDataException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyEarningsCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualEarningsCache();
		
		EarningsData earningsData;
		
		try {
			earningsData = serializer
					.deserialize(
							quoterlyCache.read(new CacheWaterMarkLevel(syncState)), 
							EarningsData.class
					);
			
			log.info("Quaterly earnings report for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			earningsData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(earningsData.getAnnualEarnings());
			Collections.sort(earningsData.getQuarterlyEarnings());
			
			quoterlyCache.update(
					earningsData.getQuarterlyEarnings().get(earningsData.getQuarterlyEarnings().size() - 1),
					serializer.serialize(earningsData));
			
			annualCache.update(earningsData.getAnnualEarnings().get(earningsData.getAnnualEarnings().size() - 1));
			
			log.info("Quaterly earnings report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return earningsData;
	}

	public EarningsData gatherFromAcquirer(String tickerSymbol) {
		EarningsData earningsData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();

			earningsData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(EarningsData.class);
			
			enreachReportsData(earningsData);
			
			return earningsData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			earningsData = new EarningsData();
			earningsData.setSymbol(tickerSymbol);
			
			return earningsData;
		}
		
		
	}
	
	private void enreachReportsData(EarningsData earningsData) {
		enreach(earningsData.getAnnualEarnings(), earningsData.getSymbol());
		enreach(earningsData.getQuarterlyEarnings(), earningsData.getSymbol());
	}
	
	private <T extends ReportData> void enreach(List<T> reports, String tickerSymbol) {
		for(ReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "earnings";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
	
}
