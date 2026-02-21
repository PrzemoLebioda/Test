package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly10YearSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660589540399624575L;

	public TreasuryYealdWeekly10YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
