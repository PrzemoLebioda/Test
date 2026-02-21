package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly07YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly07YearSynchronizationOrderedNotification extends Notification<TreasuryYealdMonthly07YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8420499595410036203L;

	public TreasuryYealdMonthly07YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdMonthly07YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
