package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly03MonthSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly03MonthSynchronizedNotification extends Notification<TreasuryYealdMonthly03MonthSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1180358697760799454L;

	public TreasuryYealdMonthly03MonthSynchronizedNotification(
			UUID id,
			TreasuryYealdMonthly03MonthSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
