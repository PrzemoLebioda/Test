package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday05MinSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteInterday05MinSynchronizedNotification extends Notification<ExchangeQuoteInterday05MinSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 355653343994228592L;

	public ExchangeQuoteInterday05MinSynchronizedNotification(UUID id,
			ExchangeQuoteInterday05MinSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
