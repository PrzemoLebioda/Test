package com.comida.sia.indicators.port.messaging.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.gdp.GdpQuarterSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class GdpQuarterSynchronizedNotification extends Notification<GdpQuarterSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7319048502704569859L;

	public GdpQuarterSynchronizedNotification(UUID id, GdpQuarterSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
