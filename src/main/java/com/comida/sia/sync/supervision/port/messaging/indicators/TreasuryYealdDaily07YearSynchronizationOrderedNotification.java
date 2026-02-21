package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdDaily07YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdDaily07YearSynchronizationOrderedNotification extends Notification<TreasuryYealdDaily07YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 317813127517756498L;

	public TreasuryYealdDaily07YearSynchronizationOrderedNotification(
			UUID id,
			TreasuryYealdDaily07YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
