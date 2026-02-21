package com.comida.sia.fundamentals.port.messaging.income;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.income.IncomeStatementQuarterReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class IncomeStatementQuarterReportSynchronizedNotification extends Notification<IncomeStatementQuarterReportSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3778258334885306896L;

	public IncomeStatementQuarterReportSynchronizedNotification(UUID id,
			IncomeStatementQuarterReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
