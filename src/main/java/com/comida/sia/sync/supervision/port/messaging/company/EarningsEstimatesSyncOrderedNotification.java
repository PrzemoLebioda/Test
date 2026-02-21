package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsEstimatesSyncOrderedDomainEvent;

public class EarningsEstimatesSyncOrderedNotification extends Notification<EarningsEstimatesSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793558643950232746L;

	public EarningsEstimatesSyncOrderedNotification(UUID id, EarningsEstimatesSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
