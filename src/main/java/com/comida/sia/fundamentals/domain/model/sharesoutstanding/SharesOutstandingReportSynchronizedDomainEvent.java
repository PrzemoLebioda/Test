package com.comida.sia.fundamentals.domain.model.sharesoutstanding;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class SharesOutstandingReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {
	
	public SharesOutstandingReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.SHARES_OUTSTANDING_REPORT_SYNCED;
	}

}
