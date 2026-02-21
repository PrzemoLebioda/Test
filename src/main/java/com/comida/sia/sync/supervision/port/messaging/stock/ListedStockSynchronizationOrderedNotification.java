package com.comida.sia.sync.supervision.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.stock.ListedStockSynchronizationOrderedDomainEvent;

public class ListedStockSynchronizationOrderedNotification extends Notification<ListedStockSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5370995456729183834L;

	public ListedStockSynchronizationOrderedNotification(
			UUID id,
			ListedStockSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
