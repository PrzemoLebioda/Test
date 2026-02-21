package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.InflationAnnualSynchronizationOrderedDomainEvent;

public class InflationAnnualSynchronizationOrderedNotification extends Notification<InflationAnnualSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 528181489489669789L;

	public InflationAnnualSynchronizationOrderedNotification(UUID id,
			InflationAnnualSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
