package com.comida.sia.fundamentals.domain.model.corpoevents.splits;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CorporateSplitEventSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {
	
	
	public CorporateSplitEventSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.CALENDAR_SPLITS_SYNCED;
	}
}
