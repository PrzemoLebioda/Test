package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly05YearSynchronizationOrderedDomainEvent;

public class TreasuryYealdMonthly05YearSynchronizationOrderedNotifiaction extends Notification<TreasuryYealdMonthly05YearSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844146032606217324L;

	public TreasuryYealdMonthly05YearSynchronizationOrderedNotifiaction(
			UUID id,
			TreasuryYealdMonthly05YearSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
