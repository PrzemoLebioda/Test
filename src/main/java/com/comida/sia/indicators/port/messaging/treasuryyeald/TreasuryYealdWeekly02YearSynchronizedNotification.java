package com.comida.sia.indicators.port.messaging.treasuryyeald;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly02YearSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class TreasuryYealdWeekly02YearSynchronizedNotification extends Notification<TreasuryYealdWeekly02YearSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1820894439230123994L;

	public TreasuryYealdWeekly02YearSynchronizedNotification(
			UUID id,
			TreasuryYealdWeekly02YearSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
