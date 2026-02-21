package com.comida.sia.indicators.port.messaging.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.gdp.GdpPerCapitaQuarterSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class GdpPerCapitaQuarterSynchronizedNotification extends Notification<GdpPerCapitaQuarterSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6345442882281560686L;

	public GdpPerCapitaQuarterSynchronizedNotification(UUID id, GdpPerCapitaQuarterSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
