package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly02YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly02YearSynchronizationOrderedNotification extends Notification<TreasuryYealdMonthly02YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1304109714500353822L;

	public TreasuryYealdMonthly02YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdMonthly02YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
