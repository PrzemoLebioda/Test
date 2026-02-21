package com.comida.sia.sync.supervision.domain.model.calendar;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class EarningsCalendarSynchronizationOrderedDomainEvent extends SyncDomainEvent {
	
	public EarningsCalendarSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.CALENDAR_EARNINGS_SYNC_ORDERED;
	}

}
