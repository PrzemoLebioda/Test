package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.GDP_QUARTER_PER_CAPITA_SYNC_ORDERED;
	}

}
