package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.CpiSemiannualSynchronizationOrderedDomainEvent;

public class CpiSemiannualSynchronizationOrderedNotification extends Notification<CpiSemiannualSynchronizationOrderedDomainEvent>{

	private static final long serialVersionUID = 8377977035021675447L;

	public CpiSemiannualSynchronizationOrderedNotification(UUID id,
			CpiSemiannualSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
		// TODO Auto-generated constructor stub
	}

}
