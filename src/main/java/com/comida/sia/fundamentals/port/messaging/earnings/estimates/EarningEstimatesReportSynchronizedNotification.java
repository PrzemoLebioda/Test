package com.comida.sia.fundamentals.port.messaging.earnings.estimates;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class EarningEstimatesReportSynchronizedNotification extends Notification<EarningEstimatesReportSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7452303082087316298L;

	public EarningEstimatesReportSynchronizedNotification(UUID id,
			EarningEstimatesReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
