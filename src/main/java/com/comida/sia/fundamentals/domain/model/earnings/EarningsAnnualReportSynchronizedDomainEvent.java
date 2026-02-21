package com.comida.sia.fundamentals.domain.model.earnings;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class EarningsAnnualReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {
	
	public EarningsAnnualReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.EARNINGS_ANNUAL_REPORT_SYNCED;
	}

}
