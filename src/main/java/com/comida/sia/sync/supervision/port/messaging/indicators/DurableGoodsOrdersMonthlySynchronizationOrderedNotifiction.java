package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent;

public class DurableGoodsOrdersMonthlySynchronizationOrderedNotifiction extends Notification<DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6365942930323008465L;

	public DurableGoodsOrdersMonthlySynchronizationOrderedNotifiction(UUID id,
			DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
