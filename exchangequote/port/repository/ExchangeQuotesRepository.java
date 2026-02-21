package com.comida.sia.exchangequote.port.repository;

import com.comida.sia.exchangequote.domain.model.ExchangeQuote;

public interface ExchangeQuotesRepository {
	void store(ExchangeQuote exchangeQuote);
	void update(ExchangeQuote exchangeQuote);
	
}
