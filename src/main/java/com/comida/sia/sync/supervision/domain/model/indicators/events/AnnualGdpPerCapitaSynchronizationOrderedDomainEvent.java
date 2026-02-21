package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class AnnualGdpPerCapitaSynchronizationOrderedDomainEvent extends SyncDomainEvent{

	public AnnualGdpPerCapitaSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.GDP_ANNUAL_PER_CAPITA_SYNC_ORDERED;
	}

}
