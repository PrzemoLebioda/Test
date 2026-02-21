package com.comida.sia.exchangequote.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.exchangequote.application.InterdayExchangeQuoteApplicationService;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntryCvsRecordParser;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotesInterdayAcquirer;
import com.comida.sia.exchangequote.port.acquirer.Interval;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cvstool.AcquirerCvsDataReader;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.opencsv.exceptions.CsvException;

@Component("AlfavantageExchangeQuotesInterdayAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageExchangeQuotesInterdayAcquirer implements ExchangeQuotesInterdayAcquirer {
	
	private static final Logger log = LogManager.getLogger(InterdayExchangeQuoteApplicationService.class);
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&interval={timeInterval}&month={appliedPeriod}&outputsize={outputSize}&apikey={apiKeyString}&datatype={dataType}";
	private static String FUNCTION = "TIME_SERIES_INTRADAY";
	//private static String API_KEY = "168OG68UETGDN1D3"; //"demo"
	private static String DATA_TYPE = "csv"; 			//"json"
	private static String OUTPUT_SIZE = "full";
	
	private static Boolean WITH_HEADER = true;
	
	@Override
	public List<ExchangeQuotationEntry> gatherExchangeQuoteFor(String symbol, Interval interval, String month) throws IOException, CsvException, ParseException {
		CacheFileManager cacheFileManager = getCacheFileManager(symbol, "interday_adjusted", interval, month);
		
		if(cacheFileManager.canReadCashe()) {
			log.info("Exchange quotes for " + symbol + ": interday " + interval + " ," + month + " gathered from cache");
			return getExchangeQuotesEntryList(cacheFileManager.readFile());
		} else {
			log.info("Exchange quotes for " + symbol + ": interday " + interval + " ," + month + " gathered from alfavantage");
			cacheFileManager.deleteFile();
			String exchangeQuotations = gatherExchangeQuotesFromAcquirer(symbol, interval, month);
			cacheFileManager.writeFile(exchangeQuotations);
			
			return getExchangeQuotesEntryList(exchangeQuotations);
		}
	}

	private String gatherExchangeQuotesFromAcquirer(String symbol, Interval interval, String month){
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("functionName", FUNCTION);
		params.put("apiKeyString", env.getProperty("apiKey"));
		params.put("outputSize", OUTPUT_SIZE);
		params.put("dataType", DATA_TYPE);
		params.put("keywordForSearch", symbol);
		params.put("timeInterval", interval.getName());
		params.put("appliedPeriod", month);
		
		RestClient restClient = RestClient.create();
		
		String dailyExchangeQuotesCvsData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(String.class);
		
		return dailyExchangeQuotesCvsData;
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol, String name, Interval interval, String month) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\quotations\\";
		String fileName = name + "_" + interval + "_" + month;
		
		return new CacheFileManager(relativePath, fileName, FileType.CSV, PeriodType.DAILY);
	}

	private List<ExchangeQuotationEntry> getExchangeQuotesEntryList(String csvData) throws IOException, CsvException, ParseException {
		try {
			return getAcquirerCvsDataReader(csvData).getParsedDataList();
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			
			return new ArrayList<>();
		}
	}
	
	private AcquirerCvsDataReader<ExchangeQuotationEntry, ExchangeQuotationEntryCvsRecordParser> getAcquirerCvsDataReader(String csv){
		ExchangeQuotationEntryCvsRecordParser cvsRecordParser = new ExchangeQuotationEntryCvsRecordParser();
		return new AcquirerCvsDataReader<ExchangeQuotationEntry, ExchangeQuotationEntryCvsRecordParser>(csv, WITH_HEADER, cvsRecordParser);
	}
}
