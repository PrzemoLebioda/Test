package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday01MinSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteInterday01MinSynchronizedNotification extends Notification<ExchangeQuoteInterday01MinSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6096177765086219990L;

	public ExchangeQuoteInterday01MinSynchronizedNotification(UUID id,
			ExchangeQuoteInterday01MinSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
