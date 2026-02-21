package com.comida.sia.fundamentals.domain.model.income;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class IncomeStatementAnnualReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {


	public IncomeStatementAnnualReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.INCOME_ANNUAL_REPORT_SYNCED;
	}

}
