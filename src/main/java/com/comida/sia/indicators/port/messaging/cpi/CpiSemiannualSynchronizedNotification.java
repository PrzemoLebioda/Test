package com.comida.sia.indicators.port.messaging.cpi;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.cpi.CpiSemiannualSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CpiSemiannualSynchronizedNotification extends Notification<CpiSemiannualSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423042305833572824L;

	public CpiSemiannualSynchronizedNotification(UUID id, CpiSemiannualSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
