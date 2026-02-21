package com.comida.sia.indicators.port.messaging.inflation;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.inflation.InflationAnnualSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class InflationAnnualSynchronizedNotification extends Notification<InflationAnnualSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178855164786886776L;

	public InflationAnnualSynchronizedNotification(UUID id, InflationAnnualSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
