package com.comida.sia.sync.supervision.port.repository;

import java.util.List;

import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuoteSynchronizationSupervisor;

public interface ExchangeQuoteSynchronizationSupervisorRepository {
	void store(ExchangeQuoteSynchronizationSupervisor supervisor);
	void update(ExchangeQuoteSynchronizationSupervisor supervisor);
	
	ExchangeQuoteSynchronizationSupervisor get(String tickerSymbol);
	List<ExchangeQuoteSynchronizationSupervisor> getAll();
}
