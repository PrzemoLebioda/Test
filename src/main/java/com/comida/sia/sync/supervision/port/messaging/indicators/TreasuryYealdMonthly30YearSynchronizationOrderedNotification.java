package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly30YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly30YearSynchronizationOrderedNotification extends Notification<TreasuryYealdMonthly30YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6902054481926470971L;

	public TreasuryYealdMonthly30YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdMonthly30YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
