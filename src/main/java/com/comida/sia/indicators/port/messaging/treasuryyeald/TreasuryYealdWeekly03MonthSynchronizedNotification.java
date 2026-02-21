package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly03MonthSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly03MonthSynchronizedNotification extends Notification<TreasuryYealdWeekly03MonthSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259528408157535040L;

	public TreasuryYealdWeekly03MonthSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly03MonthSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
