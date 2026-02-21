package com.comida.sia.sync.supervision.domain.model.company.events;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class OptionsSyncOrderedDomainEvent extends SyncTickerTaggedDomainEvent{

	public OptionsSyncOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.OPTIONS_SYNC_ORDERED;
	}

}
