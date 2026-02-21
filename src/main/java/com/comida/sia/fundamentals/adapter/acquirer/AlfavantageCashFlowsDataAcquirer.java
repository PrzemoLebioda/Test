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

import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowData;
import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowReportData;
import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowsDataAcquirer;
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
@Component("AlfavantageCashFlowsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageCashFlowsDataAcquirer implements CashFlowsDataAcquirer{
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "CASH_FLOW";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<CashFlowData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public CashFlowData gatherCashFlowsDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		CashFlowData cashFlowData = null;
		
		if(cacheFileManager.canReadCashe()) {
			cashFlowData = serializer.deserialize(cacheFileManager.readFile(), CashFlowData.class);
			
			log.info("Cash flow report for " + tickerSymbol + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			cashFlowData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(cashFlowData));
			
			log.info("Cash flow report for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return cashFlowData;
	}
	
	@Override
	public CashFlowData gatherCashFlowsDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case CASH_FLOW_ANNUAL_REPORT:
				return gatherAnnualCashFlowSheet(tickerSymbol, syncState);
			case CASH_FLOW_QUARTER_REPORT:
				return gatherQuarterlyCashFlowSheet(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private CashFlowData gatherQuarterlyCashFlowSheet(String tickerSymbol, SynchronizationStateDto syncState) throws IOException, ObsoleteCashFileException, LackOfNewDataException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyCashFlowCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualCashFlowCache();
		
		CashFlowData cashFlowData;
		
		try {
			cashFlowData = serializer
					.deserialize(
							quoterlyCache.read(new CacheWaterMarkLevel(syncState)), 
							CashFlowData.class
					);
			
			log.info("Quaterly cash flow report for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			cashFlowData = gatherFromAcquirer(tickerSymbol);
			
			Collections.sort(cashFlowData.getQuarterlyReports());
			Collections.sort(cashFlowData.getAnnualReports());
			
			quoterlyCache.update(
					cashFlowData.getQuarterlyReports().get(cashFlowData.getQuarterlyReports().size() - 1), 
					serializer.serialize(cashFlowData));
			
			annualCache.update(cashFlowData.getAnnualReports().get(cashFlowData.getAnnualReports().size() - 1));
			
			log.info("Quaterly cash flow report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return cashFlowData;
	}

	private CashFlowData gatherAnnualCashFlowSheet(String tickerSymbol, SynchronizationStateDto syncState) throws IOException, ObsoleteCashFileException, LackOfNewDataException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyCashFlowCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualCashFlowCache();
		
		CashFlowData cashFlowData;
		
		try {
			cashFlowData = serializer
					.deserialize(
							annualCache.read(new CacheWaterMarkLevel(syncState)), 
							CashFlowData.class
					);
			
			log.info("Annual cash flow report for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			cashFlowData = gatherFromAcquirer(tickerSymbol);
			
			Collections.sort(cashFlowData.getQuarterlyReports());
			Collections.sort(cashFlowData.getAnnualReports());
			
			annualCache.update(
					cashFlowData.getAnnualReports().get(cashFlowData.getAnnualReports().size() - 1), 
					serializer.serialize(cashFlowData));
			
			quoterlyCache.update(cashFlowData.getQuarterlyReports().get(cashFlowData.getQuarterlyReports().size() - 1));
			
			log.info("Annual cash flow report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}

		return cashFlowData;
	}

	private CashFlowData gatherFromAcquirer(String tickerSymbol) {
		CashFlowData cashFlowData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();
			cashFlowData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(CashFlowData.class);	
			
			enrichReportsData(cashFlowData);
			
			return cashFlowData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			cashFlowData = new CashFlowData();
			cashFlowData.setSymbol(tickerSymbol);
			
			return cashFlowData;
		}
		
	}

	private void enrichReportsData(CashFlowData cashFlowData) {
		enrich(cashFlowData.getAnnualReports(), cashFlowData.getSymbol());
		enrich(cashFlowData.getQuarterlyReports(), cashFlowData.getSymbol());
	}
	
	private void enrich(List<CashFlowReportData> reports, String tickerSymbol) {
		for(CashFlowReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "cash_flow";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}

	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
