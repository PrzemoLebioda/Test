package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily07YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdDaily07YearSynchronizedNotification extends Notification<TreasuryYealdDaily07YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 317813127517756498L;

	public TreasuryYealdDaily07YearSynchronizedNotification(
			UUID id,
			TreasuryYealdDaily07YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
