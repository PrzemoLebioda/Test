package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.UnemploymentRateMonthlySynchronizationOrderedDomainEvent;

public class UnemploymentRateMonthlySynchronizationOrderedNotification extends Notification<UnemploymentRateMonthlySynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6567512873411285278L;

	public UnemploymentRateMonthlySynchronizationOrderedNotification(UUID id,
			UnemploymentRateMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
