package com.comida.sia.fundamentals.port.application.corpoevents.splits;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CorporateSplitEventService {
	void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState);
}
