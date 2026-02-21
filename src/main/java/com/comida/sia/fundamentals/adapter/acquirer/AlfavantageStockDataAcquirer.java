package com.comida.sia.fundamentals.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.fundamentals.port.acquirer.stock.StockData;
import com.comida.sia.fundamentals.port.acquirer.stock.StockDataAcquirer;
import com.comida.sia.fundamentals.port.acquirer.stock.StockDataCvsRecordParser;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheDataNotExistsException;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.CacheWaterMarkLevel;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sharedkernel.cvstool.AcquirerCvsDataReader;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageStockDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageStockDataAcquirer implements StockDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI_FOR_LISTING_STATUS = "https://www.alphavantage.co/query?function={functionName}&state={listingState}&apikey={apiKeyString}";
	private static String LISTING_STATUS_FUNCTION = "LISTING_STATUS";
	private static String ACTIVE = "active";
	private static String DELISTED = "delisted";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private static Boolean WITH_HEADER = true;
	
	private enum Scope{
		LISTED,
		DELISTED;
	}
	
	@Override
	public List<StockData> gatherListedStockData() throws IOException, CsvException, ParseException {
		CacheFileManager cacheFileManager = getCacheFileManager("listing_status");
		List<StockData> stockDataList;
		
		if(cacheFileManager.canReadCashe()) {
			stockDataList = getCompanyDataList(cacheFileManager.readFile());
			
			log.info("Listed stock gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			String listedStockData = gatherListedStockDataFromAcquirer();
			cacheFileManager.writeFile(listedStockData);
			
			stockDataList = getCompanyDataList(listedStockData);
			
			log.info("Listed stock gathered from alfavantage");
		}
		
		return stockDataList;
	}
	
	@Override
	public List<StockData> gatherDelistedStockData() throws IOException, CsvException, ParseException {
		CacheFileManager cacheFileManager = getCacheFileManager("delisting_status");
		List<StockData> stockDataList;
		
		if(cacheFileManager.canReadCashe()) {
			stockDataList = getCompanyDataList(cacheFileManager.readFile());
			
			log.info("Delisted stock gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			String delistedStockData = gatherDelistedStockDataFromAcquirer();
			cacheFileManager.writeFile(delistedStockData);
			
			stockDataList = getCompanyDataList(delistedStockData);
			
			log.info("Delisted stock gathered from alfavantage");
		}
		
		return stockDataList;
	}
	
	@Override
	public List<StockData> gatherListedStockData(SynchronizationStateDto syncState) throws IOException, CsvException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case LISTING_STATUS:
				return gatherListedStocks(syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private List<StockData> gatherListedStocks(SynchronizationStateDto syncState) throws IOException, ParseException {
		Cache cache = getFundamentalsCacheAssembler().assemblyListedStockCache();
		List<StockData> stockDataList;
		
		try {
			stockDataList = getCompanyDataList(
					cache.read(new CacheWaterMarkLevel(syncState))
				);
			
			log.info("Listed stock gathered from cache");
			
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException | CsvException e) {
			stockDataList = gatherFromAcquirerWithCacheUpdate(Scope.LISTED, cache);
			
			log.info("Listed stock read from alfavantage due to " + e.getMessage());
		}
		
		return stockDataList;
	}

	@Override
	public List<StockData> gatherDelistedStockData(SynchronizationStateDto syncState) throws IOException, CsvException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case DELISTING_STATUS:
				return gatherDelistedStocks(syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private List<StockData> gatherDelistedStocks(SynchronizationStateDto syncState) throws IOException, ParseException {
		Cache cache = getFundamentalsCacheAssembler().assemblyDelistedStockCache();
		List<StockData> stockDataList;
		
		try {
			stockDataList = getCompanyDataList(
					cache.read(new CacheWaterMarkLevel(syncState))
				);
			
			log.info("Delisted stock gathered from cache");
			
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException | CsvException e) {
			stockDataList = gatherFromAcquirerWithCacheUpdate(Scope.DELISTED, cache);
			
			log.info("Delisted stock read from alfavantage due to " + e.getMessage());
		}
		
		return stockDataList;
	}
	
	private List<StockData> gatherFromAcquirerWithCacheUpdate(Scope scope, Cache cache) {
		try {
			String cvsContent = gatherFromAcquirer(scope);
			List<StockData> stockDataList = getCompanyDataList(cvsContent);
			Collections.sort(stockDataList);
			
			cache.update(stockDataList.get(
					stockDataList.size() - 1), 
					cvsContent);
			
			return stockDataList;
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			
			return null;
		}
	}
	
	private String gatherFromAcquirer(Scope scope){
		switch(scope) {
			case LISTED:
				return gatherListedStockDataFromAcquirer();
			case DELISTED:
				return gatherDelistedStockDataFromAcquirer();
			default:
				throw new IllegalArgumentException("Not supported synchronization type");
		}
	}

	private String gatherListedStockDataFromAcquirer(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("functionName", LISTING_STATUS_FUNCTION);
		params.put("listingState", ACTIVE);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		String ListedCompanyCvsData = restClient.get()
				.uri(URI_FOR_LISTING_STATUS, params)
				.retrieve()
				.body(String.class);
		
		return ListedCompanyCvsData;
	}
	
	private String gatherDelistedStockDataFromAcquirer(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("functionName", LISTING_STATUS_FUNCTION);
		params.put("listingState", DELISTED);
		params.put("apiKeyString", env.getProperty("apiKey"));
		
		RestClient restClient = RestClient.create();
		
		String ListedCompanyCvsData = restClient.get()
				.uri(URI_FOR_LISTING_STATUS, params)
				.retrieve()
				.body(String.class);
		
		return ListedCompanyCvsData;
	}
	

	private List<StockData> getCompanyDataList(String csvData) throws IOException, CsvException, ParseException {
		try {
			return getAcquirerCvsDataReader(csvData).getParsedDataList();
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			
			return new ArrayList<>();
		}
	}
	
	private AcquirerCvsDataReader<StockData, StockDataCvsRecordParser> getAcquirerCvsDataReader(String csv){
		StockDataCvsRecordParser cvsRecordParser = new StockDataCvsRecordParser();
		return new AcquirerCvsDataReader<StockData, StockDataCvsRecordParser>(csv, WITH_HEADER, cvsRecordParser);
	}
	
	private CacheFileManager getCacheFileManager(String name) {
		String relativePath = "listing status\\";
		String fileName = name;
		
		return new CacheFileManager(relativePath, fileName, FileType.CSV, PeriodType.QUARTERLY);
	}

	private FundamentalsCacheAssembler getFundamentalsCacheAssembler() {
		String relativePath = "listing status\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
