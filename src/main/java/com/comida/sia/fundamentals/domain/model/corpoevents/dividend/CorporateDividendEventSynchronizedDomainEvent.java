package com.comida.sia.fundamentals.domain.model.corpoevents.dividend;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CorporateDividendEventSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public CorporateDividendEventSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.CALENDAR_DIVIDEND_SYNCED;
	}
}
