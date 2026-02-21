package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetAnnualReportSyncOrderedDomainEvent;

public class BalanceSheetAnnualReportSyncOrderedNotification extends Notification<BalanceSheetAnnualReportSyncOrderedDomainEvent> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6551373227669232083L;

	public BalanceSheetAnnualReportSyncOrderedNotification(UUID id, 
			BalanceSheetAnnualReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
