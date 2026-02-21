package com.comida.sia.fundamentals.port.application.sharesoutstanding;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface SharesOutstandingService {
	void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState);
}
