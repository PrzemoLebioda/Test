package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.CashflowAnnualReportSyncOrderedDomainEvent;

public class CashflowAnnualReportSyncOrderedNotification extends Notification<CashflowAnnualReportSyncOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4614320225958477146L;

	public CashflowAnnualReportSyncOrderedNotification(UUID id, CashflowAnnualReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
