package com.comida.sia.fundamentals.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.fundamentals.port.acquirer.company.CompanyDataAcquirer;
import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
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
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageCompanyDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageCompanyDataAcquirer implements CompanyDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI_FOR_OVERVIEW = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}";
	private static String OVERVIEW_FUNCTION = "OVERVIEW";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";

	private GeneralSerializer<CompanyDetailsData> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public CompanyDetailsData gatherCompanyDetailsDataFor(String tickerSymbol) throws IOException {
		CacheFileManager cacheFileManager = getCacheFileManager(tickerSymbol);
		CompanyDetailsData companyDetailsData = null;
		
		if(cacheFileManager.canReadCashe()) {
			companyDetailsData = serializer.deserialize(cacheFileManager.readFile(), CompanyDetailsData.class);
			
			log.info("Company details for " + tickerSymbol + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			companyDetailsData = gatherFromAcquirer(tickerSymbol);
			cacheFileManager.writeFile(serializer.serialize(companyDetailsData));
			
			log.info("Company details for " + tickerSymbol + " gathered from alfavantage");
		}
		
		return companyDetailsData;
	}
	
	@Override
	public CompanyDetailsData gatherCompanyDetailsDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case COMPANY_KEY_METRICS:
				return gatherComapnyDetailsData(tickerSymbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private CompanyDetailsData gatherComapnyDetailsData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache companyDataCache = getFundamentalsCacheAssembler(tickerSymbol).assemblyCompanyDataCache();
		
		CompanyDetailsData companyDetailsData;
		
		try {
			companyDetailsData = serializer
						.deserialize(
							companyDataCache.read(new CacheWaterMarkLevel(syncState)), 
							CompanyDetailsData.class
						);
			
			log.info("Company details for " + tickerSymbol + " gathered from cache");
			
		} catch (ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			companyDetailsData = gatherFromAcquirer(tickerSymbol);
			
			companyDataCache.update(
					companyDetailsData, 
					serializer.serialize(companyDetailsData));
			
			log.info("Company details for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage() );

		}
		
		return companyDetailsData;
	}

	private CompanyDetailsData gatherFromAcquirer(String tickerSymbol) { 
		CompanyDetailsData companyData;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", OVERVIEW_FUNCTION);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();
			String companyDataAsText = restClient.get()
					.uri(URI_FOR_OVERVIEW, params)
					.retrieve()
					.body(String.class);	
			
			companyDataAsText = companyDataAsText
				.replace("52WeekHigh", "Week52High")
				.replace("52WeekLow", "Week52Low")
				.replace("50DayMovingAverage", "Day50MovingAverage")
				.replace("50DayMovingAverage", "Day50MovingAverage")
				.replace("200DayMovingAverage", "Day200MovingAverage");
			
			Gson gson = new Gson();
			companyData = gson.fromJson(companyDataAsText, CompanyDetailsData.class);
			
			return companyData;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			companyData = null;
			return companyData;
		}
	}

	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "key_metrics";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}

	private FundamentalsCacheAssembler getFundamentalsCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
