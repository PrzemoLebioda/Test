package com.comida.sia.fundamentals.port.messaging.balancesheet;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetAnnualReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class BalanceSheetAnnualReportSynchronizedNotification extends Notification<BalanceSheetAnnualReportSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860367034696368847L;

	public BalanceSheetAnnualReportSynchronizedNotification(UUID id,
			BalanceSheetAnnualReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
