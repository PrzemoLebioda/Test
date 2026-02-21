package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.CpiMonthlySynchronizationOrderedDomainEvent;

public class CpiMonthlySynchronizationOrderedNotification extends Notification<CpiMonthlySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2145767263745310937L;

	public CpiMonthlySynchronizationOrderedNotification(UUID id, CpiMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
