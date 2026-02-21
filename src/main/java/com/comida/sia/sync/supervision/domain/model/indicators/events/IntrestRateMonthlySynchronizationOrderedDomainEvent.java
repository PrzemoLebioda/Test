package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class IntrestRateMonthlySynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public IntrestRateMonthlySynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.INTREST_RATE_MONTHLY_SYNC_ORDERED;
	}

}
