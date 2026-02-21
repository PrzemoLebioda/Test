package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarSplitsSyncOrderedDomainEvent;

public class CalendarSplitsSyncOrderedNotification extends Notification<CalendarSplitsSyncOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6708607216794258376L;

	public CalendarSplitsSyncOrderedNotification(UUID id, CalendarSplitsSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
