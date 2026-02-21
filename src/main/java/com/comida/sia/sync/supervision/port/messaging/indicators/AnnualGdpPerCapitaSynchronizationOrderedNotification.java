package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.AnnualGdpPerCapitaSynchronizationOrderedDomainEvent;

public class AnnualGdpPerCapitaSynchronizationOrderedNotification extends Notification<AnnualGdpPerCapitaSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540947037627206442L;

	public AnnualGdpPerCapitaSynchronizationOrderedNotification(UUID id,
			AnnualGdpPerCapitaSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
