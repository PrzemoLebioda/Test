package com.comida.sia.fundamentals.port.messaging.cashflow;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowQuarterReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CashFlowQuarterReportSynchronizedNotification extends Notification<CashFlowQuarterReportSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2175958178117184665L;

	public CashFlowQuarterReportSynchronizedNotification(UUID id,
			CashFlowQuarterReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
