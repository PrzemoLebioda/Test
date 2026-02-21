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

import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetData;
import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetReportData;
import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetsDataAcquirer;
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
@Component("AlfavantageBalanceSheetsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageBalanceSheetsDataAcquirer implements BalanceSheetsDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "BALANCE_SHEET";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<BalanceSheetData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public BalanceSheetData gatherBalanceSheetsDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		BalanceSheetData balanceSheetData = null;
		
		if(cacheFileManager.canReadCashe()) {
			balanceSheetData = serializer.deserialize(cacheFileManager.readFile(), BalanceSheetData.class);
			log.info("Balance sheet report for " + tickerSymbol + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			balanceSheetData = gatherFromAccquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(balanceSheetData));
			log.info("Balance sheet report for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return balanceSheetData;
	}
	
	@Override
	public BalanceSheetData gatherBalanceSheetsDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case BALANCE_SHEET_ANNUAL_REPORT:
				return gatherAnnualBalanceSheet(tickerSymbol, syncState);
			case BALANCE_SHEET_QUARTER_REPORT:
				return gatherQuarterlyBalanceSheet(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private BalanceSheetData gatherQuarterlyBalanceSheet(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyBalanceSheetCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualBalanceSheetCache();
		
		BalanceSheetData balanceSheetsData;
		
		try {
			balanceSheetsData = serializer
						.deserialize(
							quoterlyCache.read(new CacheWaterMarkLevel(syncState)), 
							BalanceSheetData.class
						);
			log.info("Quarterly balance sheet report for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			balanceSheetsData = gatherFromAccquirer(tickerSymbol);
			Collections.sort(balanceSheetsData.getQuarterlyReports());
			Collections.sort(balanceSheetsData.getAnnualReports());
			
			quoterlyCache.update(
					balanceSheetsData.getQuarterlyReports().get(balanceSheetsData.getQuarterlyReports().size() - 1), 
					serializer.serialize(balanceSheetsData));
			
			annualCache.update(balanceSheetsData.getAnnualReports().get(balanceSheetsData.getAnnualReports().size() - 1));
			
			log.info("Quaterly balance sheet report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());

		}
		
		return balanceSheetsData;
	}
	
	private BalanceSheetData gatherAnnualBalanceSheet(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyBalanceSheetCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualBalanceSheetCache();
		
		BalanceSheetData balanceSheetsData;
		
		try {
			balanceSheetsData = serializer
						.deserialize(
							annualCache.read(new CacheWaterMarkLevel(syncState)), 
							BalanceSheetData.class
						);
			log.info("Annual balance sheet report for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			balanceSheetsData = gatherFromAccquirer(tickerSymbol);
			Collections.sort(balanceSheetsData.getQuarterlyReports());
			Collections.sort(balanceSheetsData.getAnnualReports());
			
			annualCache.update(
					balanceSheetsData.getAnnualReports().get(balanceSheetsData.getAnnualReports().size() - 1), 
					serializer.serialize(balanceSheetsData));
			
			quoterlyCache.update(balanceSheetsData.getQuarterlyReports().get(balanceSheetsData.getQuarterlyReports().size() - 1));
			
			log.info("Annual balance sheet report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());

		}
		
		return balanceSheetsData;
	}
	
	private BalanceSheetData gatherFromAccquirer(String tickerSymbol) {	
		BalanceSheetData balanceSheetsData;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();
			balanceSheetsData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(BalanceSheetData.class);	
			
			enreachReportsData(balanceSheetsData);
			
			return balanceSheetsData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			balanceSheetsData = new BalanceSheetData();
			balanceSheetsData.setSymbol(tickerSymbol);
			
			return balanceSheetsData;
		}
	}
	
	
	private void enreachReportsData(BalanceSheetData balanceSheetData) {
		enreach(balanceSheetData.getAnnualReports(), balanceSheetData.getSymbol());
		enreach(balanceSheetData.getQuarterlyReports(), balanceSheetData.getSymbol());
	}
	
	private void enreach(List<BalanceSheetReportData> reports, String tickerSymbol) {
		for(BalanceSheetReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "balance_sheet";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
