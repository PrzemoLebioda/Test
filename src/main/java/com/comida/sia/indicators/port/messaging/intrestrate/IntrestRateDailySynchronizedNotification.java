package com.comida.sia.indicators.port.messaging.intrestrate;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.intrestrate.IntrestRateDailySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class IntrestRateDailySynchronizedNotification extends Notification<IntrestRateDailySynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779222698719332611L;

	public IntrestRateDailySynchronizedNotification(UUID id, IntrestRateDailySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
