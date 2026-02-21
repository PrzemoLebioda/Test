package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday60MinSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteInterday60MinSynchronizedNotification extends Notification<ExchangeQuoteInterday60MinSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3718630288790193984L;

	public ExchangeQuoteInterday60MinSynchronizedNotification(UUID id,
			ExchangeQuoteInterday60MinSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
