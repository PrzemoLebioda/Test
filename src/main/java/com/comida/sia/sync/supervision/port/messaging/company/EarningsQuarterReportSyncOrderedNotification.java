package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsQuarterReportSyncOrderedDomainEvent;

public class EarningsQuarterReportSyncOrderedNotification extends Notification<EarningsQuarterReportSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4767888388579209582L;

	public EarningsQuarterReportSyncOrderedNotification(UUID id, EarningsQuarterReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
