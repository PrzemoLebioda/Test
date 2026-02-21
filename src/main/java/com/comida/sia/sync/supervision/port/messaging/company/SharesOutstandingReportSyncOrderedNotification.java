package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.SharesOutstandingReportSyncOrderedDomainEvent;

public class SharesOutstandingReportSyncOrderedNotification extends Notification<SharesOutstandingReportSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6745934064827676605L;

	public SharesOutstandingReportSyncOrderedNotification(UUID id,
			SharesOutstandingReportSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
