package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily03MonthSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily03MonthSynchronizedNotification extends Notification<TreasuryYealdDaily03MonthSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5264221881080913705L;

	public TreasuryYealdDaily03MonthSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily03MonthSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
