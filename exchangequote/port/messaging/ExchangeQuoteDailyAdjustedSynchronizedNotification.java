package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteDailyAdjustedSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class ExchangeQuoteDailyAdjustedSynchronizedNotification extends Notification<ExchangeQuoteDailyAdjustedSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8696853486965339352L;

	public ExchangeQuoteDailyAdjustedSynchronizedNotification(UUID id,
			ExchangeQuoteDailyAdjustedSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
