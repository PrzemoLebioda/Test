package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly05YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdMonthly05YearSynchronizedNotifiaction extends Notification<TreasuryYealdMonthly05YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844146032606217324L;

	public TreasuryYealdMonthly05YearSynchronizedNotifiaction(
			UUID id,
			TreasuryYealdMonthly05YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
