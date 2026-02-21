package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday30MinSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteInterday30MinSynchronizedNotification extends Notification<ExchangeQuoteInterday30MinSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3491811504791735951L;

	public ExchangeQuoteInterday30MinSynchronizedNotification(UUID id,
			ExchangeQuoteInterday30MinSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
