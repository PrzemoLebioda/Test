package com.comida.sia.indicators.port.messaging.intrestrate;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.intrestrate.IntrestRateMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class IntrestRateMonthlySynchronizedNotification extends Notification<IntrestRateMonthlySynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2765849145749516431L;

	public IntrestRateMonthlySynchronizedNotification(UUID id, IntrestRateMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
