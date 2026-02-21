package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly05YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly05YearSynchronizedNotification extends Notification<TreasuryYealdWeekly05YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4134168654634410068L;

	public TreasuryYealdWeekly05YearSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly05YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
