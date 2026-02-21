package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly10YearSynchronizationOrderedNotification extends Notification<TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188032949186531668L;

	public TreasuryYealdMonthly10YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
