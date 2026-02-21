package com.comida.sia.fundamentals.port.messaging.corpoevents.earnings;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.EarningsCalendarSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class EarningsCalendarSynchronizedNotification extends Notification<EarningsCalendarSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991583330521123398L;

	public EarningsCalendarSynchronizedNotification(UUID id, EarningsCalendarSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
