package com.comida.sia.fundamentals.port.messaging.cashflow;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowAnnualReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CashFlowAnnualReportSynchronizedNotification extends Notification<CashFlowAnnualReportSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3303111878586934533L;

	public CashFlowAnnualReportSynchronizedNotification(UUID id, 
			CashFlowAnnualReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
