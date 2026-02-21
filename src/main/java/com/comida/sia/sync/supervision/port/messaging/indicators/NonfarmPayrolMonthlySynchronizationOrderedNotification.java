package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.NonfarmPayrolMonthlySynchronizationOrderedDomainEvent;

public class NonfarmPayrolMonthlySynchronizationOrderedNotification extends Notification<NonfarmPayrolMonthlySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4860417127085783220L;

	public NonfarmPayrolMonthlySynchronizationOrderedNotification(UUID id,
			NonfarmPayrolMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
