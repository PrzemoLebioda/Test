package com.comida.sia.fundamentals.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.stock.ListedStockSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ListedStocksSynchronizedNotification extends Notification<ListedStockSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8206551667280200846L;

	public ListedStocksSynchronizedNotification(UUID id, ListedStockSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
