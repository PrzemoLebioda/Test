package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly07YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly07YearSynchronizedNotification extends Notification<TreasuryYealdMonthly07YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8420499595410036203L;

	public TreasuryYealdMonthly07YearSynchronizedNotification(
			UUID id,
			TreasuryYealdMonthly07YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
