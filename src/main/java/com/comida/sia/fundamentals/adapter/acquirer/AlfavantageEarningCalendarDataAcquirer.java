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

import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningCalendarDataAcquirer;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningEventData;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningsEventsDataCvsRecordParser;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheDataNotExistsException;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.CacheWaterMarkLevel;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sharedkernel.cvstool.AcquirerCvsDataReader;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageEarningCalendarDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageEarningCalendarDataAcquirer implements EarningCalendarDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&horizon={horizon}&apikey={apiKeyString}";
	private static String FUNCTION_NAME = "EARNINGS_CALENDAR";
	private static String HORIZON = "3month";
	//private static String API_KEY = "168OG68UETGDN1D3";
	//private static String API_KEY = "demo";
	
	private static Boolean WITH_HEADER = true;
	
	@Override
	public List<EarningEventData> gatherEarningCalendarData() throws IOException, CsvException, ParseException {
		CacheFileManager cacheFileManager = getCacheFileManager();
		List<EarningEventData> earningEventDataList;
		
		if(cacheFileManager.canReadCashe()) {
			earningEventDataList = getEarningEventDataList(cacheFileManager.readFile());
			
			log.info("Earnings calendar read from cashe");
		} else {

			cacheFileManager.deleteFile();
			String earningEventsCalendar = gatherFromAcquirer();
			cacheFileManager.writeFile(earningEventsCalendar);
			
			earningEventDataList = getEarningEventDataList(earningEventsCalendar);
			
			log.info("Earnings calendar read from alfavantage");
		}
		
		return earningEventDataList;
	}
	
	@Override
	public List<EarningEventData> gatherEarningCalendarData(SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case EARNINGS_CALENDAR:
				return gatherEarningsCalendarData(syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private List<EarningEventData> gatherEarningsCalendarData(SynchronizationStateDto syncState) throws IOException, ParseException, ObsoleteCashFileException, LackOfNewDataException {
		Cache cache = getFundamentalsCacheAssembler().assemblyEarningsCalendarCache();
		List<EarningEventData> earningsCalendar;
		
		try {
			earningsCalendar = getEarningEventDataList(
					cache.read(new CacheWaterMarkLevel(syncState))
				);
			
			log.info("Earnings calendar read from cashe");
			
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException | CsvException e) {
			earningsCalendar = gatherFromAcquirerWithCacheUpdate(cache);
			
			log.info("Earnings calendar read from alfavantage due to " + e.getMessage());
		}
		
		return earningsCalendar;
	}
	
	private List<EarningEventData> gatherFromAcquirerWithCacheUpdate(Cache cache){
		try {
			String earningsCalendarCvsContent = gatherFromAcquirer();
			List<EarningEventData> earningsCalendar = getEarningEventDataList(earningsCalendarCvsContent);
			Collections.sort(earningsCalendar);
			
			cache.update(earningsCalendar.get(
					earningsCalendar.size() - 1), 
					earningsCalendarCvsContent);
			
			return earningsCalendar;
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			
			return null;
		}
	}

	private String gatherFromAcquirer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("functionName", FUNCTION_NAME);
		params.put("apiKeyString", env.getProperty("apiKey"));
		params.put("horizon", HORIZON);
		
		RestClient restClient = RestClient.create();
		
		String earningsCvsData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(String.class);

		return earningsCvsData;
	}
	
	private CacheFileManager getCacheFileManager() {
		String relativePath = "stocks\\fundamentals\\";
		String fileName = "earnings_calendar";
		
		return new CacheFileManager(relativePath, fileName, FileType.CSV, PeriodType.QUARTERLY);
	}
	
	
	private AcquirerCvsDataReader<EarningEventData, EarningsEventsDataCvsRecordParser> getAcquirerCvsDataReader(String csv){
		EarningsEventsDataCvsRecordParser cvsRecordParser = new EarningsEventsDataCvsRecordParser();
		return new AcquirerCvsDataReader<EarningEventData, EarningsEventsDataCvsRecordParser>(csv, WITH_HEADER, cvsRecordParser);
	}
	
	private List<EarningEventData> getEarningEventDataList(String csvData) throws IOException, CsvException, ParseException {
		try {
			return getAcquirerCvsDataReader(csvData).getParsedDataList();
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			
			return new ArrayList<>();
		}
	}
	
	private FundamentalsCacheAssembler getFundamentalsCacheAssembler() {
		String relativePath = "stocks\\";
		
		return new FundamentalsCacheAssembler(relativePath);
	}
}
