/**
 * 
 */
package com.comida.sia.exchangequote.port.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public interface ExchangeQuotesAcquirer {
	public List<ExchangeQuotationEntry> gatherExchangeQuoteFor(String symbol) throws IOException, CsvException, ParseException;
	
}