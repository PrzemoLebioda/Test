package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateMonthlySynchronizationOrderedDomainEvent;

public class IntrestRateMonthlySynchronizationOrderedNotification extends Notification<IntrestRateMonthlySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2224367521433629048L;

	public IntrestRateMonthlySynchronizationOrderedNotification(UUID id,
			IntrestRateMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
		// TODO Auto-generated constructor stub
	}

}
