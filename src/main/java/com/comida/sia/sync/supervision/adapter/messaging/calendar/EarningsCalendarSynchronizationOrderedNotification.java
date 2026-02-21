package com.comida.sia.sync.supervision.adapter.messaging.calendar;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.calendar.EarningsCalendarSynchronizationOrderedDomainEvent;

public class EarningsCalendarSynchronizationOrderedNotification extends Notification<EarningsCalendarSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8467791851799535374L;

	public EarningsCalendarSynchronizationOrderedNotification(
			UUID id,
			EarningsCalendarSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
