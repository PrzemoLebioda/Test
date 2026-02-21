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

import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitCalendarData;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventData;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventDataAcquirer;
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
@Component("AlfavantageSplitEventDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageSplitEventDataAcquirer implements SplitEventDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}";
	private static String FUNCTION_NAME = "SPLITS";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<SplitCalendarData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public SplitCalendarData gatherSplitCalendarData(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		SplitCalendarData splitCalendarData = null;
		
		if(cacheFileManager.canReadCashe()) {
			splitCalendarData = serializer.deserialize(cacheFileManager.readFile(), SplitCalendarData.class);
			
			log.info("Split calendar for " + tickerSymbol + " gathered from cashe");
		} else {
			cacheFileManager.deleteFile();
			splitCalendarData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(splitCalendarData));
			
			log.info("Split calendar for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return splitCalendarData;
	}
	
	@Override
	public SplitCalendarData gatherSplitCalendarData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case SPLIT_EVENT:
				return gatherSplitEventData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private SplitCalendarData gatherSplitEventData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache cache = getFundamentalsCacheAssembler(tickerSymbol).assemblySplitCache();
	
		SplitCalendarData splitCalendarData;
		
		try {
			splitCalendarData = serializer
					.deserialize(
							cache.read(new CacheWaterMarkLevel(syncState)), 
							SplitCalendarData.class
					);
			
			log.info("Split calendar for " + tickerSymbol + " gathered from cashe");
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			splitCalendarData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(splitCalendarData.getData());
			
			cache.update(
					splitCalendarData.getData().get(splitCalendarData.getData().size() - 1),
					serializer.serialize(splitCalendarData));
			
			log.info("Split calendar for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return splitCalendarData;
	}

	private SplitCalendarData gatherFromAcquirer(String tickerSymbol) {
		SplitCalendarData splitCalendarData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();

			splitCalendarData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(SplitCalendarData.class);

			enrichReportsData(splitCalendarData);
			
			return splitCalendarData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			splitCalendarData = new SplitCalendarData();
			splitCalendarData.setSymbol(tickerSymbol);
			
			return splitCalendarData;
		}
	}
	
	private void enrichReportsData(SplitCalendarData splitCalendarData) {
		enrich(splitCalendarData.getData(), splitCalendarData.getSymbol());
	}
	
	private void enrich(List<SplitEventData> reports, String tickerSymbol) {
		for(SplitEventData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "split";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}

}
