package com.comida.sia.fundamentals.domain.model.earnings.estimation;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class EarningEstimatesReportSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public EarningEstimatesReportSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.EARNINGS_ESTIMATES_SYNCED;
	}

}
