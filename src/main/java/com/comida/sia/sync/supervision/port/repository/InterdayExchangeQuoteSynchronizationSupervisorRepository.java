package com.comida.sia.sync.supervision.port.repository;

import java.util.List;

import com.comida.sia.sync.supervision.domain.model.exchangequote.InterdayExchangeQuoteSynchronizationSupervisor;

public interface InterdayExchangeQuoteSynchronizationSupervisorRepository {
	void store(InterdayExchangeQuoteSynchronizationSupervisor supervisor);
	void update(InterdayExchangeQuoteSynchronizationSupervisor supervisor);
	
	InterdayExchangeQuoteSynchronizationSupervisor get(String tickerSymbol);
	List<InterdayExchangeQuoteSynchronizationSupervisor> getAll();
}
