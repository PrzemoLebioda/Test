package com.comida.sia.fundamentals.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.stock.StockListedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class StockListedNotification extends Notification<StockListedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2323334211648618132L;

	public StockListedNotification(UUID id, StockListedDomainEvent payload) {
		super(id, payload);
	}

}
