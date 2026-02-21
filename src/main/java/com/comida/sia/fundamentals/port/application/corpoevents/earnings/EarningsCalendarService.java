package com.comida.sia.fundamentals.port.application.corpoevents.earnings;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface EarningsCalendarService {
	void synchronizeEvents(SynchronizationStateDto syncState);
}
