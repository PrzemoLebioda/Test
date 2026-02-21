package com.comida.sia.options.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.options.port.acquirer.OptionAcquirer;
import com.comida.sia.options.port.acquirer.OptionData;
import com.comida.sia.options.port.acquirer.OptionDataCvsRecordParser;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cvstool.AcquirerCvsDataReader;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageOptionAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageOptionAcquirer implements OptionAcquirer {

	@Autowired
	private Environment env;
	
	private static String OPTION_URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&date={since}&apikey={apiKeyString}&datatype={dataType}";
	
	private static String FUNCTION = "HISTORICAL_OPTIONS";
	private static String DATA_TYPE = "csv"; //json
	
	private static Boolean WITH_HEADER = true;
	
	@Override
	public List<OptionData> gatherOptionDataFor(String symbol, Date since) throws IOException, CsvException, ParseException {
		Date gatheringFromDate = getGatheringFromDate(since);
		CacheFileManager cacheFileManager = getCacheFileManager(symbol, gatheringFromDate);
		 
		List<OptionData> optionDataList;
		
		if(cacheFileManager.canReadCashe()) {
			optionDataList = getCompanyDataList(cacheFileManager.readFile());
			
			log.info("Options for " + symbol +  " and date " + since + " gathered from cache");
		} else {
			cacheFileManager.deleteFile();
			String optionResult = gatherOptionDataFromAcquirer(symbol, gatheringFromDate);
			cacheFileManager.writeFile(optionResult);
			
			optionDataList =  getCompanyDataList(optionResult);
			
			log.info("Options for " + symbol +  " and date " + since + " gathered from alfavantage");
		}
		
		return optionDataList;
	}
	
	private CacheFileManager getCacheFileManager(String name, Date since) {
		String tickerSymbol = name;
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\options\\";
		String fileName = name + "_options_" + TranslationConcern.getFormattedDate("yyyy-MM-dd_HHmmss", since);
		
		return new CacheFileManager(relativePath, fileName, FileType.CSV, PeriodType.QUARTERLY);
	}
	
	private String gatherOptionDataFromAcquirer(String symbol, Date since) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("keywordForSearch", symbol);
		params.put("since", TranslationConcern.getFormattedDate("yyyy-MM-dd", since));
		params.put("functionName", FUNCTION);
		params.put("apiKeyString", env.getProperty("apiKey"));
		params.put("dataType", DATA_TYPE);
		
		RestClient restClient = RestClient.create();
		
		String optionsCsvData = restClient.get()
				.uri(OPTION_URI, params)
				.retrieve()
				.body(String.class);	
		
		return optionsCsvData;
	}
	
	private Date getGatheringFromDate(Date dateFrom) {
		
		if(dateFrom == null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.set(Calendar.YEAR, 2008);
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			
			return calendar.getTime();
		} else {
			return dateFrom;
		}
	}
	
	private List<OptionData> getCompanyDataList(String csvData) throws IOException, CsvException, ParseException {
		try {
			return getAcquirerCvsDataReader(csvData).getParsedDataList();
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			
			return new ArrayList<>();
		}
	}

	private AcquirerCvsDataReader<OptionData, OptionDataCvsRecordParser> getAcquirerCvsDataReader(String csv){
		OptionDataCvsRecordParser optionDataCvsRecordParser = new OptionDataCvsRecordParser();
		return new AcquirerCvsDataReader<OptionData, OptionDataCvsRecordParser>(csv, WITH_HEADER, optionDataCvsRecordParser);
	}
}
