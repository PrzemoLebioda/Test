package com.comida.sia.indicators.port.messaging.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.gdp.GdpPerCapitaAnnualSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class GdpPerCapitaAnnualSynchronizedNotification extends Notification<GdpPerCapitaAnnualSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4932829437292767147L;

	public GdpPerCapitaAnnualSynchronizedNotification(UUID id, GdpPerCapitaAnnualSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
