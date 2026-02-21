package com.comida.sia.fundamentals.port.messaging.corpoevents.splits;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.corpoevents.splits.CorporateSplitEventSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CorporateSplitEventSynchronizedNotification extends Notification<CorporateSplitEventSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1853731586990419805L;

	public CorporateSplitEventSynchronizedNotification(UUID id, CorporateSplitEventSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
