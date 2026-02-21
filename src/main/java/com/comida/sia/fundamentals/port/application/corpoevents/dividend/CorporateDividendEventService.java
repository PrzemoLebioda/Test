package com.comida.sia.fundamentals.port.application.corpoevents.dividend;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CorporateDividendEventService {
	void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState);
}
