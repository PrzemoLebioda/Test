package com.comida.sia.indicators.port.messaging.cpi;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.cpi.CpiMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CpiMonthlySynchronizedNotification extends Notification<CpiMonthlySynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734670075534577366L;

	public CpiMonthlySynchronizedNotification(UUID id, CpiMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
