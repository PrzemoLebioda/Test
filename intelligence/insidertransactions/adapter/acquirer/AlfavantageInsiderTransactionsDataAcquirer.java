package com.comida.sia.intelligence.insidertransactions.adapter.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionResult;
import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionsDataAcquirer;
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
@Component("AlfavantageInsiderTransactionsDataAcquirer")
@PropertySource({"classpath:application.properties"})
public class AlfavantageInsiderTransactionsDataAcquirer implements InsiderTransactionsDataAcquirer {
	
	@Autowired
	private Environment env;
	
	private static String URI = "https://www.alphavantage.co/query?function={functionName}&symbol={keywordForSearch}&apikey={apiKeyString}";
	
	private static String FUNCTION = "INSIDER_TRANSACTIONS";

	private GeneralSerializer<InsiderTransactionResult> serializer = new GeneralSerializer<>(true, true);
	
	@Override
	public InsiderTransactionResult gatherInsiderTransactionsFor(String symbol) throws IOException{
		CacheFileManager cacheFileManager = getCacheFileManager(symbol);
		InsiderTransactionResult insiderTransactionResult = null;
		
		if(cacheFileManager.canReadCashe()) {
			insiderTransactionResult = serializer.deserialize(cacheFileManager.readFile(), InsiderTransactionResult.class);
			
			log.info("Insiders transactions for " + symbol + " read from cashe");
		} else {
			cacheFileManager.deleteFile();
			insiderTransactionResult = gatherFromAcquirer(symbol);
			cacheFileManager.writeFile(serializer.serialize(insiderTransactionResult));
			
			log.info("Insiders transactions for " + symbol + " read from alfavantage");
		}
		
		return insiderTransactionResult;
	}
	
	@Override
	public InsiderTransactionResult gatherInsiderTransactionsFor(String symbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope {
		switch(syncState.getScope()) {
			case INSIDERS_TRANSACTIONS:
				return gatherInsidersTransactionsData(symbol, syncState);
			default:
				throw new NotSupportedSynchronizationScope(syncState.getScope());
		}
	}
	
	private InsiderTransactionResult gatherInsidersTransactionsData(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException {
		Cache cache = getIntelligenceCacheAssembler(tickerSymbol).assemblyInsidersTransactionCache();
		InsiderTransactionResult insiderTransactionResult;
		
		try {
			insiderTransactionResult = serializer
					.deserialize(
						cache.read(new CacheWaterMarkLevel(syncState)), 
						InsiderTransactionResult.class
					);
			
			log.info("Insiders transactions for " + tickerSymbol + " read from cashe");
			
		} catch(ObsoleteCashFileException | ObsoleteCashDataException | CacheDataNotExistsException e) {
			insiderTransactionResult = gatherFromAcquirer(tickerSymbol);
			Collections.sort(insiderTransactionResult.getData());
			
			cache.update(
					insiderTransactionResult.getData().get(insiderTransactionResult.getData().size() - 1), 
					serializer.serialize(insiderTransactionResult));
			
			log.info("Insiders transactions for " + tickerSymbol + " gathered from alfavantage due to " + e.getMessage());

		}
		
		return insiderTransactionResult;
	}

	private InsiderTransactionResult gatherFromAcquirer(String tickerSymbol) {
		InsiderTransactionResult insiderTransactionResult;
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("keywordForSearch", tickerSymbol);
			params.put("functionName", FUNCTION);
			params.put("apiKeyString", env.getProperty("apiKey"));
			
			RestClient restClient = RestClient.create();
			
			insiderTransactionResult = restClient.get()
					.uri(URI, params)
					.retrieve()
					.body(InsiderTransactionResult.class);
			
			return insiderTransactionResult;
		} catch(Exception e) {
			log.warn("Ticker symbol: " + tickerSymbol + e.getMessage(), e);
			
			insiderTransactionResult = new InsiderTransactionResult();
			
			return insiderTransactionResult;
		}
	}
	
	private CacheFileManager getCacheFileManager(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		String fileName = "insiders_transactions";
		
		return new CacheFileManager(relativePath, fileName, FileType.JSON, PeriodType.QUARTERLY);
	}

	private IntelligenceCacheAssembler getIntelligenceCacheAssembler(String tickerSymbol) {
		String relativePath = "stocks\\" + tickerSymbol.charAt(0) + "\\" + tickerSymbol + "\\fundamentals\\";
		
		return new IntelligenceCacheAssembler(relativePath);
	}
}
