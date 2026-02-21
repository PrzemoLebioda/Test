package com.comida.sia.fundamentals.port.messaging.corpoevents.dividend;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.corpoevents.dividend.CorporateDividendEventSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CorporateDividendEventSynchronizedNotification extends Notification<CorporateDividendEventSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767151816913578799L;

	public CorporateDividendEventSynchronizedNotification(UUID id, CorporateDividendEventSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
