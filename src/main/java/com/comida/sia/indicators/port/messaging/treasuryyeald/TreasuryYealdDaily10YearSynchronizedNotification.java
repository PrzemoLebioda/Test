package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily10YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily10YearSynchronizedNotification extends Notification<TreasuryYealdDaily10YearSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 967971821851631970L;

	public TreasuryYealdDaily10YearSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily10YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
