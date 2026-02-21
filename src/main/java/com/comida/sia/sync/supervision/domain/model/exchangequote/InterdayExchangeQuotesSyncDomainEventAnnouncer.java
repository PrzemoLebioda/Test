package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SyncTaggedDomainEventAnnouncer;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class InterdayExchangeQuotesSyncDomainEventAnnouncer extends SyncTaggedDomainEventAnnouncer {

	public InterdayExchangeQuotesSyncDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState,
			String tag) {
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
			case EXCHANGE_QUOTE_INTERDAY_60_MIN:
				return new ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_INTERDAY_30_MIN:
				return new ExchangeQuotesInterday30MinSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_INTERDAY_15_MIN:
				return new ExchangeQuotesInterday15MinSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_INTERDAY_05_MIN:
				return new ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EXCHANGE_QUOTE_INTERDAY_01_MIN:
				return new ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			default:
				throw new IllegalArgumentException("Subject: " + syncState.getScope() + " not supported!");
		}
	}
}
