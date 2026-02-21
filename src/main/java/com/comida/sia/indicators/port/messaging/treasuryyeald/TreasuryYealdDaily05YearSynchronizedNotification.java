package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily05YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily05YearSynchronizedNotification extends Notification<TreasuryYealdDaily05YearSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3917591376713436583L;

	public TreasuryYealdDaily05YearSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily05YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
