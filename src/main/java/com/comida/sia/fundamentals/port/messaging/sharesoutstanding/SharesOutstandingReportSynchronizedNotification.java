package com.comida.sia.fundamentals.port.messaging.sharesoutstanding;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.sharesoutstanding.SharesOutstandingReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class SharesOutstandingReportSynchronizedNotification extends Notification<SharesOutstandingReportSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083160065170687453L;

	public SharesOutstandingReportSynchronizedNotification(UUID id, 
			SharesOutstandingReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
