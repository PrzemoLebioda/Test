package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly03MonthSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly03MonthSynchronizationOrderedNotification extends Notification<TreasuryYealdMonthly03MonthSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1180358697760799454L;

	public TreasuryYealdMonthly03MonthSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdMonthly03MonthSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
