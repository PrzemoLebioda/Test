package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateWeeklySynchronizationOrderedDomainEvent;

public class IntrestRateWeeklySynchronizationOrderedNotification extends Notification<IntrestRateWeeklySynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8306692852571349221L;

	public IntrestRateWeeklySynchronizationOrderedNotification(UUID id,
			IntrestRateWeeklySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
