package com.comida.sia.fundamentals.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.stock.DelistedStockSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class DelistedStocksSynchronizedNotification extends Notification<DelistedStockSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1519041479036511504L;

	public DelistedStocksSynchronizedNotification(UUID id, DelistedStockSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
