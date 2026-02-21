package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly30YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly30YearSynchronizedNotification extends Notification<TreasuryYealdWeekly30YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9179284585716354787L;

	public TreasuryYealdWeekly30YearSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly30YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
