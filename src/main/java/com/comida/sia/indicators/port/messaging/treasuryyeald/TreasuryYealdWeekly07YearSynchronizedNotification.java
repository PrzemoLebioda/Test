package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly07YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly07YearSynchronizedNotification extends Notification<TreasuryYealdWeekly07YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 961559891842952165L;

	public TreasuryYealdWeekly07YearSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly07YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
