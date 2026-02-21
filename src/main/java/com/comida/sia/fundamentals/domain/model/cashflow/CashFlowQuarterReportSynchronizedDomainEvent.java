package com.comida.sia.fundamentals.domain.model.cashflow;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CashFlowQuarterReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public CashFlowQuarterReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.CASH_FLOW_QUARTER_REPORT_SYNCED;
	}
}
