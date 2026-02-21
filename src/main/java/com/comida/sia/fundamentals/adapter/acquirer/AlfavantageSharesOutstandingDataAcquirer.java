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

import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingData;
import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingDataAcquirer;
import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingReportData;
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
@Component("AlfavantageSharesOutstandingDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageSharesOutstandingDataAcquirer implements SharesOutstandingDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}";
	private static String FUNCTION_NAME = "SHARES_OUTSTANDING";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<SharesOutstandingData> serializer = new GeneralSerializer<>(true, true);
	
	
	@Override
	public SharesOutstandingData gatherSharesOutstandingData(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		SharesOutstandingData sharesOutstandingData = null;
		
		if(cacheFileManager.canReadCashe()) {
			sharesOutstandingData = serializer.deserialize(cacheFileManager.readFile(), SharesOutstandingData.class);
			
			log.info("Shares outstanding for " + tickerSymbol + " gathered from cashe");
		} else {
			cacheFileManager.deleteFile();
			sharesOutstandingData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(sharesOutstandingData));
			
			log.info("Shares outstanding for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return sharesOutstandingData;
	}
	
	@Override
	public SharesOutstandingData gatherSharesOutstandingData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case SHARES_OUTSTANDING_REPORT:
				return gatherReportData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private SharesOutstandingData gatherReportData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache cache = getFundamentalsCacheAssembler(tickerSymbol).assemblySharesOutstandingCache();
		
		SharesOutstandingData sharesOutstandingReportData;
		
		try {
			sharesOutstandingReportData = serializer
					.deserialize(
							cache.read(new CacheWaterMarkLevel(syncState)), 
							SharesOutstandingData.class
					);
			
			log.info("Shares outstanding for " + tickerSymbol + " gathered from cashe");
		
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			sharesOutstandingReportData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(sharesOutstandingReportData.getData());
			
			cache.update(
					sharesOutstandingReportData.getData().get(sharesOutstandingReportData.getData().size() - 1),
					serializer.serialize(sharesOutstandingReportData));
			
			log.info("Shares outstanding for " + tickerSymbol + " gathered from alfavantage, due to " + e.getMessage());
		}
		
		return sharesOutstandingReportData;
	}

	private SharesOutstandingData gatherFromAcquirer(String tickerSymbol) {
		SharesOutstandingData sharesOutstandingData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();

			sharesOutstandingData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(SharesOutstandingData.class);

			enrichReportsData(sharesOutstandingData);
			
			return sharesOutstandingData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + " " + e.getMessage(), e);
			
			sharesOutstandingData = new SharesOutstandingData();
			sharesOutstandingData.setSymbol(tickerSymbol);
			
			return sharesOutstandingData;
		}	
	}
	
	private void enrichReportsData(SharesOutstandingData sharesOutstandingData) {
		enrich(sharesOutstandingData.getData(), sharesOutstandingData.getSymbol());
	}
	
	private void enrich(List<SharesOutstandingReportData> reports, String tickerSymbol) {
		for(SharesOutstandingReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "shares_outstanding";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}

	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
