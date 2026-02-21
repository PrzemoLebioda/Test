package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteMonthlyAdjustedSynchronizedNotification extends Notification<ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1665336201174098729L;

	public ExchangeQuoteMonthlyAdjustedSynchronizedNotification(UUID id,
			ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
