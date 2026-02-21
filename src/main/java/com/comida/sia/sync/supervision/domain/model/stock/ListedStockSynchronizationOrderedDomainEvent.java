package com.comida.sia.sync.supervision.domain.model.stock;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class ListedStockSynchronizationOrderedDomainEvent extends SyncDomainEvent {
	
	public ListedStockSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.LISTED_STOCKS_SYNC_ORDERED;
	}

}
