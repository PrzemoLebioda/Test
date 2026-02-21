package com.comida.sia.intelligence.insidertransactions.port.messaging;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.gdp.GdpQuarterSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class GdpQuarterSynchronizedNotification extends Notification<GdpQuarterSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095622182628554176L;

	public GdpQuarterSynchronizedNotification(UUID id, GdpQuarterSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
