package com.comida.sia.fundamentals.port.messaging.earnings;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.earnings.EarningsAnnualReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class EarningsAnnualReportSynchronizedNotification extends Notification<EarningsAnnualReportSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3832298091586032631L;

	public EarningsAnnualReportSynchronizedNotification(UUID id, 
			EarningsAnnualReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
