package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class CpiSemiannualSynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public CpiSemiannualSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.CPI_SEMIANNUAL_SYNC_ORDERED;
	}

}
