package com.comida.sia.indicators.port.messaging.retailsales;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.retailsales.RetailSalesMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class RetailSalesMonthlySynchronizedNotification extends Notification<RetailSalesMonthlySynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8234935216879603582L;

	public RetailSalesMonthlySynchronizedNotification(UUID id, RetailSalesMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
