package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsAnnualReportSyncOrderedDomainEvent;

public class EarningsAnnualReportSyncOrderedNotification extends Notification<EarningsAnnualReportSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8740139393756125625L;

	public EarningsAnnualReportSyncOrderedNotification(UUID id, EarningsAnnualReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
