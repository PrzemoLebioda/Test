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
import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementData;
import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementDataAcquirer;
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
@Component("AlfavantageIncomeStatementsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageIncomeStatementsDataAcquirer implements IncomeStatementDataAcquirer {

	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}"; 
	private static String FUNCTION_NAME = "INCOME_STATEMENT";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private GeneralSerializer<IncomeStatementData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public IncomeStatementData gatherIncomeStatementDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		IncomeStatementData incomeStatementData = null;
		
		if(cacheFileManager.canReadCashe()) {
			incomeStatementData = serializer.deserialize(cacheFileManager.readFile(), IncomeStatementData.class);
			
			log.info("Income report for " + tickerSymbol + " gathered from cashe");
		} else {
			cacheFileManager.deleteFile();
			incomeStatementData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(incomeStatementData));

			log.info("Income report for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return incomeStatementData;
	}
	
	@Override
	public IncomeStatementData gatherIncomeStatementDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		
		switch(syncState.getScope()) {
			case INCOME_STATEMENT_ANNUAL_REPORT:
				return getAnnualData(tickerSymbol, syncState);
			case INCOME_STATEMENT_QUARTER_REPORT:
				return getQuarterData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private IncomeStatementData getAnnualData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyIncomeCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualIncomeCache();
		
		IncomeStatementData incomeData;
		
		try {
			incomeData = serializer
					.deserialize(
							annualCache.read(new CacheWaterMarkLevel(syncState)), 
							IncomeStatementData.class
					);
			
			log.info("Income report for " + tickerSymbol + " gathered from cashe");
		
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			incomeData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(incomeData.getAnnualReports());
			Collections.sort(incomeData.getQuarterlyReports());
			
			annualCache.update(
					incomeData.getAnnualReports().get(incomeData.getAnnualReports().size() - 1),
					serializer.serialize(incomeData));
			
			quoterlyCache.update(incomeData.getQuarterlyReports().get(incomeData.getQuarterlyReports().size() - 1));
			
			log.info("Income report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return incomeData;
	}
	
	private IncomeStatementData getQuarterData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache quoterlyCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyQuarterlyIncomeCache();
		Cache annualCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyAnnualIncomeCache();
				
		IncomeStatementData earningsData;
				
		try {
			earningsData = serializer
					.deserialize(
							quoterlyCache.read(new CacheWaterMarkLevel(syncState)), 
							IncomeStatementData.class
					);
			
			log.info("Income report for " + tickerSymbol + " gathered from cashe");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			earningsData = gatherFromAcquirer(tickerSymbol);
			Collections.sort(earningsData.getAnnualReports());
			Collections.sort(earningsData.getQuarterlyReports());
			
			quoterlyCache.update(
					earningsData.getQuarterlyReports().get(earningsData.getQuarterlyReports().size() - 1),
					serializer.serialize(earningsData));
			
			annualCache.update(earningsData.getAnnualReports().get(earningsData.getAnnualReports().size() - 1));
			
			log.info("Income report for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());
		}
		
		return earningsData;
	}

	private IncomeStatementData gatherFromAcquirer(String tickerSymbol) {
		IncomeStatementData incomeStatementData;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION_NAME);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();

			incomeStatementData = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(IncomeStatementData.class);
			
			enreachReportsData(incomeStatementData);
			
			return incomeStatementData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			incomeStatementData = new IncomeStatementData(); 
			incomeStatementData.setSymbol(tickerSymbol);
			
			return incomeStatementData;
		}
		
	}

	private void enreachReportsData(IncomeStatementData incomeStatementData) {
		enrich(incomeStatementData.getAnnualReports(), incomeStatementData.getSymbol());
		enrich(incomeStatementData.getQuarterlyReports(), incomeStatementData.getSymbol());
	}
	
	private <T extends ReportData> void enrich(List<T> reports, String tickerSymbol) {
		for(ReportData reportData : reports) {
			reportData.enrich(tickerSymbol);
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "income_statement";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}

}
