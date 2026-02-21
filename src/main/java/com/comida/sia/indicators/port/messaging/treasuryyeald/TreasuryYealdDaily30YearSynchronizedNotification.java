package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily30YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily30YearSynchronizedNotification extends Notification<TreasuryYealdDaily30YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2275568663284200314L;

	public TreasuryYealdDaily30YearSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily30YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
