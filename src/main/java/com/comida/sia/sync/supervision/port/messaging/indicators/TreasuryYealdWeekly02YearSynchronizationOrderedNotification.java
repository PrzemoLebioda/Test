package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly02YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdWeekly02YearSynchronizationOrderedNotification extends Notification<TreasuryYealdWeekly02YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1820894439230123994L;

	public TreasuryYealdWeekly02YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdWeekly02YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
