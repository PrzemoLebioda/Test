package com.comida.sia.indicators.port.messaging.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.gdp.GdpAnnualSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class GdpAnnualSynchronizedNotification extends Notification<GdpAnnualSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2966386947584631920L;

	public GdpAnnualSynchronizedNotification(UUID id, GdpAnnualSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
