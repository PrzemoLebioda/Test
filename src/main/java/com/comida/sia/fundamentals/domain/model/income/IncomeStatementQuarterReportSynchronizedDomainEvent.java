package com.comida.sia.fundamentals.domain.model.income;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class IncomeStatementQuarterReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public IncomeStatementQuarterReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.INCOME_QUARTER_REPORT_SYNCED;
	}

}
