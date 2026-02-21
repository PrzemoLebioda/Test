package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetQuarterReportSyncOrderedDomainEvent;

public class BalanceSheetQuarterReportSyncOrderedNotification extends Notification<BalanceSheetQuarterReportSyncOrderedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3757566276109127423L;

	public BalanceSheetQuarterReportSyncOrderedNotification(UUID id,
			BalanceSheetQuarterReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
