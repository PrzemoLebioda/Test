package com.comida.sia.fundamentals.port.messaging.stock;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.stock.StockDelistedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class StockDelistedNotification extends Notification<StockDelistedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234754739715442619L;

	public StockDelistedNotification(UUID id, StockDelistedDomainEvent payload) {
		super(id, payload);
	}

}
