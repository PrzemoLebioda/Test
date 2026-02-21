package com.comida.sia.sync.supervision.port.repository;

import com.comida.sia.sync.supervision.domain.model.stock.StockSynchronizationSupervisor;

public interface StockSynchronizationSypervisorRepository {
	void store(StockSynchronizationSupervisor supervisor);
	void update(StockSynchronizationSupervisor supervisor);
	
	StockSynchronizationSupervisor get();
}
