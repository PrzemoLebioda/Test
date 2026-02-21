package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly07YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly07YearSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly07YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 961559891842952165L;

	public TreasuryYealdWeekly07YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly07YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
