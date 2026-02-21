package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly10YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly10YearSynchronizedNotification extends Notification<TreasuryYealdMonthly10YearSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188032949186531668L;

	public TreasuryYealdMonthly10YearSynchronizedNotification(
			UUID id,
			TreasuryYealdMonthly10YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
