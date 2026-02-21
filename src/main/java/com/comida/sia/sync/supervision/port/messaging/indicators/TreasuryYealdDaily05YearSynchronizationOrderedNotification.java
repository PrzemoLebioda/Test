package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily05YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily05YearSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily05YearSynchronizationOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3917591376713436583L;

	public TreasuryYealdDaily05YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily05YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
