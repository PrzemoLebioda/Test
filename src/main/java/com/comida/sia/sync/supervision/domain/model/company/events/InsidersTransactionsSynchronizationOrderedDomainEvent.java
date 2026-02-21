package com.comida.sia.sync.supervision.domain.model.company.events;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class InsidersTransactionsSynchronizationOrderedDomainEvent extends SyncTickerTaggedDomainEvent{

	public InsidersTransactionsSynchronizationOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.INSIDER_TRANSACTIONS_SYNC_ORDERED;
	}

}
