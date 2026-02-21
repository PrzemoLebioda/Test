package com.comida.sia.sync.supervision.domain.model.calendar;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SyncDomainEventAnnouncer;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class CalendarSyncDomainEventAnnouncer extends SyncDomainEventAnnouncer {

	public CalendarSyncDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState) {
		super(notifier, syncState);
	}

	@Override
	public void announce() {
		AssertionConcern.assertNotNull(notifier, "Notifier is neccary in order to send a domain event");
		AssertionConcern.assertNotNull(syncState, "Synchronization state is mandatory in order to provide necessary information for synchronization");
		
		notifier.notify(getDomainEvent());
	}

	private SubjectedPayload getDomainEvent() {
		switch(syncState.getScope()) {
			case EARNINGS_CALENDAR:
				return new EarningsCalendarSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			default:
				throw new IllegalArgumentException("Subject: " + syncState.getScope() + " not supported!");
		}
	}
}
