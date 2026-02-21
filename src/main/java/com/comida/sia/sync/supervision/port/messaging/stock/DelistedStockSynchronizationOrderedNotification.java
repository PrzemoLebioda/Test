package com.comida.sia.sync.supervision.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.stock.DelistedStockSynchronizationOrderedDomainEvent;

public class DelistedStockSynchronizationOrderedNotification extends Notification<DelistedStockSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7410890199753159089L;

	public DelistedStockSynchronizationOrderedNotification(
			UUID id,
			DelistedStockSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
