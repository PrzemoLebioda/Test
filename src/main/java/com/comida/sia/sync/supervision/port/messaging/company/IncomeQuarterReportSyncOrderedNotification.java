package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.IncomeQuarterReportSyncOrderedDomainEvent;

public class IncomeQuarterReportSyncOrderedNotification extends Notification<IncomeQuarterReportSyncOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7625068189497864475L;

	public IncomeQuarterReportSyncOrderedNotification(UUID id, IncomeQuarterReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
