package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily03MonthSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily03MonthSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily03MonthSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264221881080913705L;

	public TreasuryYealdDaily03MonthSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily03MonthSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
