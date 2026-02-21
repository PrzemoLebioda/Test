package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly05YearSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4134168654634410068L;

	public TreasuryYealdWeekly05YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
