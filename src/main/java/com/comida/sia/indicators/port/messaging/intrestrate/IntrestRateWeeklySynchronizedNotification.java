package com.comida.sia.indicators.port.messaging.intrestrate;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.intrestrate.IntrestRateWeeklySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class IntrestRateWeeklySynchronizedNotification extends Notification<IntrestRateWeeklySynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953478642739428952L;

	public IntrestRateWeeklySynchronizedNotification(UUID id, IntrestRateWeeklySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
