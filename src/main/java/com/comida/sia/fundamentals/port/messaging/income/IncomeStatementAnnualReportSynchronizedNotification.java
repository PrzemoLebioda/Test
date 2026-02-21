package com.comida.sia.fundamentals.port.messaging.income;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.income.IncomeStatementAnnualReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class IncomeStatementAnnualReportSynchronizedNotification extends Notification<IncomeStatementAnnualReportSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589912107662119288L;

	public IncomeStatementAnnualReportSynchronizedNotification(UUID id,
			IncomeStatementAnnualReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}
}
