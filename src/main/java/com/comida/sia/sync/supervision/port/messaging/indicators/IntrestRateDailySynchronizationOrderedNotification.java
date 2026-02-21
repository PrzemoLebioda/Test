package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateDailySynchronizationOrderedDomainEvent;

public class IntrestRateDailySynchronizationOrderedNotification extends Notification<IntrestRateDailySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6372575397052609447L;

	public IntrestRateDailySynchronizationOrderedNotification(UUID id,
			IntrestRateDailySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
