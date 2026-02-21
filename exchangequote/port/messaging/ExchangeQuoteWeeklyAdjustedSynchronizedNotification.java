package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteWeeklyAdjustedSynchronizedNotification extends Notification<ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5134620503466640063L;

	public ExchangeQuoteWeeklyAdjustedSynchronizedNotification(UUID id,
			ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent payload) {
		super(id, payload); 
	}

}
