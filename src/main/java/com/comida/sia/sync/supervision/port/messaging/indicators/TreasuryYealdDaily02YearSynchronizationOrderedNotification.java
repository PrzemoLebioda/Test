package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily02YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily02YearSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily02YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8092523924738709610L;

	public TreasuryYealdDaily02YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily02YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
