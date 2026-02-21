package com.comida.sia.intelligence.insidertransactions.domain.model;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class InsiderTransactionsSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent{
		
	public InsiderTransactionsSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.INSIDER_TRANSACTIONS_SYNCED;
	}
}
