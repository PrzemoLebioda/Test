package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly03MonthSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly03MonthSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly03MonthSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259528408157535040L;

	public TreasuryYealdWeekly03MonthSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly03MonthSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
