package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly30YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly30YearSynchronizedNotification extends Notification<TreasuryYealdMonthly30YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6902054481926470971L;

	public TreasuryYealdMonthly30YearSynchronizedNotification(
			UUID id,
			TreasuryYealdMonthly30YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
