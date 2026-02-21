package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.DURABLE_GOODS_ORDER_MONTHLY_SYNC_ORDERED;
	}

}
