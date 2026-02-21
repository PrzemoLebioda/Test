package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.IncomeAnnualReportSyncOrderedDomainEvent;

public class IncomeAnnualReportSyncOrderedNotification extends Notification<IncomeAnnualReportSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2852289712549727911L;

	public IncomeAnnualReportSyncOrderedNotification(UUID id, IncomeAnnualReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
