package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly10YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly10YearSynchronizedNotification extends Notification<TreasuryYealdWeekly10YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8660589540399624575L;

	public TreasuryYealdWeekly10YearSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly10YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
