package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday15MinSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteInterday15MinSynchronizedNotification extends Notification<ExchangeQuoteInterday15MinSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4361172145666494659L;

	public ExchangeQuoteInterday15MinSynchronizedNotification(UUID id,
			ExchangeQuoteInterday15MinSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
