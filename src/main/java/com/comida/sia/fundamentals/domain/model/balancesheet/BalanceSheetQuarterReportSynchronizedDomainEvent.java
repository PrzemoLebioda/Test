package com.comida.sia.fundamentals.domain.model.balancesheet;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class BalanceSheetQuarterReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public BalanceSheetQuarterReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.BALANCE_SHEET_QUARTER_REPORT_SYNCED;
	}

}
