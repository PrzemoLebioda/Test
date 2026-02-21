package com.comida.sia.exchangequote.port.application;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface InterdayExchangeQuoteService {
	void synchronizeInterday60MinQuotations(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeInterday30MinQuotations(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeInterday15MinQuotations(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeInterday05MinQuotations(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeInterday01MinQuotations(String tickerSymbol, SynchronizationStateDto syncState);
}
