package com.comida.sia.exchangequote.port.repository;

import java.util.Date;
import java.util.List;

import com.comida.sia.exchangequote.domain.model.ExchangeQuote;

public interface ExchangeQuoteRepository {
	
	void store(ExchangeQuote exchangeQuote);
	void update(ExchangeQuote exchangeQuote);
	
	
	List<ExchangeQuote> getQuotationsFor(String symbol, Date from, Date to);
}
