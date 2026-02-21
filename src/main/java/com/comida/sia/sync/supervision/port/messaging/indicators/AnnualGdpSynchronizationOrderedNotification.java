package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.AnnualGdpSynchronizationOrderedDomainEvent;

public class AnnualGdpSynchronizationOrderedNotification extends Notification<AnnualGdpSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7291290815490752728L;

	public AnnualGdpSynchronizationOrderedNotification(UUID id, AnnualGdpSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
