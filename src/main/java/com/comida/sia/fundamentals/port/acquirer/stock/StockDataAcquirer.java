package com.comida.sia.fundamentals.port.acquirer.stock;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

public interface StockDataAcquirer {
	/**
	 * Querying all active(listed) stocks and ETFs as of the latest trading day
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws CsvException 
	 */
	List<StockData> gatherListedStockData() throws IOException, CsvException, ParseException;
	
	/**
	 * Querying all active(listed) stock and ETFs as of the latest trading day using synchronization status
	 * 
	 * @param syncState
	 * @return
	 * @throws IOException
	 * @throws CsvException
	 * @throws ParseException
	 * @throws NotSupportedSynchronizationScope 
	 */
	List<StockData> gatherListedStockData(SynchronizationStateDto syncState) throws IOException, CsvException, ParseException, NotSupportedSynchronizationScope;
	
	/**
	 * Querying all inactive(delisted) stock and ETFs as of the latest trading day
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws CsvException 
	 */
	List<StockData> gatherDelistedStockData() throws IOException, CsvException, ParseException;
	
	/**
	 * Querying all inactive(delisted) stock and ETFs as of the latest trading day using synchronization status
	 * 
	 * @param syncState
	 * @return
	 * @throws IOException
	 * @throws CsvException
	 * @throws ParseException
	 * @throws NotSupportedSynchronizationScope 
	 */
	List<StockData> gatherDelistedStockData(SynchronizationStateDto syncState) throws IOException, CsvException, ParseException, NotSupportedSynchronizationScope;
	

}
