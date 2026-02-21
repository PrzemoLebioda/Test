package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily10YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily10YearSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily10YearSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 967971821851631970L;

	public TreasuryYealdDaily10YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily10YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
