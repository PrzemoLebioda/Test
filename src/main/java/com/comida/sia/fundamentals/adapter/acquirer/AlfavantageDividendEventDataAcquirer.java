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

import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendCalendarData;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventData;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventDataAcquirer;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
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
@Component("AlfavantageDividendEventDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageDividendEventDataAcquirer implements DividendEventDataAcquirer{
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}";
	private static String FUNCTION_NAME = "DIVIDENDS";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<DividendCalendarData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public DividendCalendarData gatherDividendCalendarDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		DividendCalendarData dividendCalendarData = null;
		
		if(cacheFileManager.canReadCashe()) {
			dividendCalendarData = serializer.deserialize(cacheFileManager.readFile(), DividendCalendarData.class);
			
			log.info("Dividend calendar for " + tickerSymbol + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			dividendCalendarData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(dividendCalendarData));
			
			log.info("Dividend calendar for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return dividendCalendarData;
	}
	
	@Override
	public DividendCalendarData gatherDividendCalendarDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case DIVIDEND_EVENT:
				return gatherDividendCalendarData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private DividendCalendarData gatherDividendCalendarData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyDividendCache();
		
		DividendCalendarData dividendCalendarData;
		
		try {
			dividendCalendarData = serializer
						.deserialize(
							annualCache.read(new CacheWaterMarkLevel(syncState)), 
							DividendCalendarData.class
						);
			
			log.info("Dividend calendar for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			dividendCalendarData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(dividendCalendarData.getData());
			
			annualCache.update(
					dividendCalendarData.getData().get(dividendCalendarData.getData().size() - 1), 
					serializer.serialize(dividendCalendarData));
			
			
			log.info("Dividend calendar for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());

		}
		
		return dividendCalendarData;
	}

	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "dividend";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}
	
	private DividendCalendarData gatherFromAcquirer(String tickerSymbol) {
		DividendCalendarData dividendCalendarData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();

			dividendCalendarData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(DividendCalendarData.class);

			enrichReportsData(dividendCalendarData);
			
			return dividendCalendarData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			dividendCalendarData = new DividendCalendarData();
			dividendCalendarData.setSymbol(tickerSymbol);
			
			return dividendCalendarData;
		}
	}
	
	private void enrichReportsData(DividendCalendarData dividendCalendarData) throws EmptyListException {
		enrich(dividendCalendarData.getData(), dividendCalendarData.getSymbol());
	}
	
	private void enrich(List<DividendEventData> reports, String tickerSymbol) throws EmptyListException {
		AssertionConcern.assertNotEmpty(reports, "Divident reports can't be empty");
		
		for(DividendEventData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}

}
