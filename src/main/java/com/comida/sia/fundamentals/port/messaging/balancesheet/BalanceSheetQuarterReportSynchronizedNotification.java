package com.comida.sia.fundamentals.port.messaging.balancesheet;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetQuarterReportSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class BalanceSheetQuarterReportSynchronizedNotification extends Notification<BalanceSheetQuarterReportSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3003301172082538290L;

	public BalanceSheetQuarterReportSynchronizedNotification(UUID id,
			BalanceSheetQuarterReportSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
