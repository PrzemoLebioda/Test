package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.CashflowQuarterReportSyncOrderedDomainEvent;

public class CashflowQuarterReportSyncOrderedNotification extends Notification<CashflowQuarterReportSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7146529615389081228L;

	public CashflowQuarterReportSyncOrderedNotification(UUID id, CashflowQuarterReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
