package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SyncTaggedDomainEventAnnouncer;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class ExchangeQuotesSyncDomainEventAnnouncer extends SyncTaggedDomainEventAnnouncer {

	public ExchangeQuotesSyncDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState, String tag) {
		super(notifier, syncState, tag);
	}

	@Override
	public void announce() {
		AssertionConcern.assertNotNull(notifier, "Notifier is neccary in order to send a domain event");
		AssertionConcern.assertNotNull(syncState, "Synchronization state is mandatory in order to provide necessary information for synchronization");
		
		notifier.notify(getDomainEvent());
	}

	private SubjectedPayload getDomainEvent() {
		switch(syncState.getScope()) {
			case EXCHANGE_QUOTE_MONTHLY:
				return new ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_WEEKLY:
				return new ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_DAILY:
				return new ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			default:
				throw new IllegalArgumentException("Subject: " + syncState.getScope() + " not supported!");
		}
	}
}
