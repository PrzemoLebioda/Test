package com.comida.sia.sync.supervision.port.application.stock;

import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public interface StockSynchronizationSupervisorService {
	public void orderSynchronization();
	
	public void calculateListedStocksCurrentSyncState(SynchronizationSummary summary);
	public void calculateDelistedStocksCurrentSyncState(SynchronizationSummary summary);
}
