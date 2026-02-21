package com.comida.sia.fundamentals.domain.model.company;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CompanyKeyMetricsSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {
	
	
	public CompanyKeyMetricsSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.COMPANY_KEY_METRICS_SYNCED;
	}
}
