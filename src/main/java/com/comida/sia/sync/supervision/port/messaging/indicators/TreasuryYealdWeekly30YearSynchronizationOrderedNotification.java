package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly30YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly30YearSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly30YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9179284585716354787L;

	public TreasuryYealdWeekly30YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly30YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
