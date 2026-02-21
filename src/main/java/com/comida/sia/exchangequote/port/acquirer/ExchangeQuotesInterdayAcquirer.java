package com.comida.sia.exchangequote.port.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public interface ExchangeQuotesInterdayAcquirer {
	public List<ExchangeQuotationEntry> gatherExchangeQuoteFor(String symbol, Interval interval, String month) throws IOException, CsvException, ParseException;
}
