package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class IntrestRateWeeklySynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public IntrestRateWeeklySynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.INTREST_RATE_WEEKLY_SYNC_ORDERED;
	}

}
