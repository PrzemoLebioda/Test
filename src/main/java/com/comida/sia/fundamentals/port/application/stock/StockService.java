package com.comida.sia.fundamentals.port.application.stock;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface StockService {
	void synchronizeListedStock(SynchronizationStateDto syncState);
	void synchronizeDelistedStock(SynchronizationStateDto syncState);
}
