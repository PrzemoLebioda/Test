package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly02YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly02YearSynchronizedNotification extends Notification<TreasuryYealdMonthly02YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1304109714500353822L;

	public TreasuryYealdMonthly02YearSynchronizedNotification(
			UUID id,
			TreasuryYealdMonthly02YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
