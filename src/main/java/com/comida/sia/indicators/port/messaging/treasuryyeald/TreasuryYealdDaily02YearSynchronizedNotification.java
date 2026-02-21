package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily02YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily02YearSynchronizedNotification extends Notification<TreasuryYealdDaily02YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8092523924738709610L;

	public TreasuryYealdDaily02YearSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily02YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
