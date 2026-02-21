package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily30YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily30YearSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily30YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2275568663284200314L;

	public TreasuryYealdDaily30YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily30YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
