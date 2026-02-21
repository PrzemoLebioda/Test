package com.comida.sia.intelligence.insidertransactions.port.application;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface InsiderTransactionService {
	void registerInsiderTransactionsFor(String tickerSymbol, SynchronizationStateDto syncState);
}
