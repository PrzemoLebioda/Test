package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarDividendSyncOrderedDomainEvent;

public class CalendarDividendSyncOrderedNotification extends Notification<CalendarDividendSyncOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899901942298446850L;

	public CalendarDividendSyncOrderedNotification(UUID id, CalendarDividendSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
