package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.QuarterlyGdpSynchronizationOrderedDomainEvent;

public class QuarterlyGdpSynchronizationOrderedNotification extends Notification<QuarterlyGdpSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3366481015828310051L;

	public QuarterlyGdpSynchronizationOrderedNotification(UUID id,
			QuarterlyGdpSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
