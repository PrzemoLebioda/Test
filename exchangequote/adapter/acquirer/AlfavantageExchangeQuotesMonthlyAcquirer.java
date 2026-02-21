package com.comida.sia.exchangequote.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationAdjustedEntryCvsRecordParser;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotesAcquirer;
import com.comida.sia.sharedkernel.cashe.CacheFileManager;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.cvstool.AcquirerCvsDataReader;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.opencsv.exceptions.CsvException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AlfavantageExchangeQuotesMonthlyAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageExchangeQuotesMonthlyAcquirer implements ExchangeQuotesAcquirer {

	//private static final Logger log = LogManager.getLogger(ExchangeQuotesSynchronizationSupervisorApplicationService.class);
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&outputsize={outputSize}&symbol={keywordForSearch}&apikey={apiKeyString}&datatype={dataType}";
	private static String FUNCTION = "TIME_SERIES_MONTHLY_ADJUSTED";
	//private static String API_KEY = "168OG68UETGDN1D3"; //"demo"
	private static String DATA_TYPE = "csv"; 			//"json"
	private static String OUTPUT_SIZE = "full";
	
	private static Boolean WITH_HEADER = true;
	
	@Override
	public List<ExchangeQuotationEntry> gatherExchangeQuoteFor(String symbol) throws IOException, CsvException, ParseException {
		CacheFileManager cacheFileManager = getCacheFileManager(symbol, "monthly_adjusted");
		
		if(cacheFileManager.canReadCashe()) {
			log.info("Exchange quotes for " + symbol + ": monthly adjusted gathered from cache");
			return getExchangeQuotesEntryList(cacheFileManager.readFile());
		} else {
			log.info("Exchange quotes for " + symbol + ": monthly adjusted gathered from alfavantage");
			cacheFileManager.deleteFile();
			String exchangeQuotations = gatherExchangeQuotesFromAcquirer(symbol);
			cacheFileManager.writeFile(exchangeQuotations);
			
			return getExchangeQuotesEntryList(exchangeQuotations);
		}
	}

	private String gatherExchangeQuotesFromAcquirer(String symbol){
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("keywordForSearch", symbol);
		params.put("functionName", FUNCTION);
		params.put("apiKeyString", env.getProperty("apiKey"));
		params.put("outputSize", OUTPUT_SIZE);
		params.put("dataType", DATA_TYPE);
		
		RestClient restClient = RestClient.create();
		
		String dailyExchangeQuotesCvsData = restClient.get()
				.uri(URI, params)
				.retrieve()
				.body(String.class);
		
		return dailyExchangeQuotesCvsData;
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol, String name) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\quotations\\";
		String fileName = name;
		
		return new CacheFileManager(relativePath, fileName, FileType.CSV, PeriodType.MONTHLY);
	}

	private List<ExchangeQuotationEntry> getExchangeQuotesEntryList(String csvData) throws IOException, CsvException, ParseException {
		try {
			return getAcquirerCvsDataReader(csvData).getParsedDataList();
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			
			return new ArrayList<>();
		}
	}
	
	private AcquirerCvsDataReader<ExchangeQuotationEntry, ExchangeQuotationAdjustedEntryCvsRecordParser> getAcquirerCvsDataReader(String csv){
		ExchangeQuotationAdjustedEntryCvsRecordParser cvsRecordParser = new ExchangeQuotationAdjustedEntryCvsRecordParser();
		return new AcquirerCvsDataReader<ExchangeQuotationEntry, ExchangeQuotationAdjustedEntryCvsRecordParser>(csv, WITH_HEADER, cvsRecordParser);
	}

}
