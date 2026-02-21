package com.comida.sia.fundamentals.port.messaging.earnings;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.earnings.EarningsQuarterReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class EarningsQuarterReportSynchronizedNotification extends Notification<EarningsQuarterReportSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482612193876958903L;

	public EarningsQuarterReportSynchronizedNotification(UUID id,
			EarningsQuarterReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
