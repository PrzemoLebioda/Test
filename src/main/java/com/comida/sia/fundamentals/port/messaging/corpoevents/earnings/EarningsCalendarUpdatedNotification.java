package com.comida.sia.fundamentals.port.messaging.corpoevents.earnings;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.EarningsCalendarUpdatedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class EarningsCalendarUpdatedNotification extends Notification<EarningsCalendarUpdatedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3620433989103444754L;

	public EarningsCalendarUpdatedNotification(UUID id, EarningsCalendarUpdatedDomainEvent payload) {
		super(id, payload);
	}

}
