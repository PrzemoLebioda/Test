package com.comida.sia.exchangequote.port.application;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

public interface AdjustedExchangeQuoteService {
	void synchronizeDailyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) throws ParseException, IOException, CsvException;
	void synchronizeWeeklyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) throws ParseException, IOException, CsvException;
	void synchronizeMonthlyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) throws ParseException, IOException, CsvException;
}
