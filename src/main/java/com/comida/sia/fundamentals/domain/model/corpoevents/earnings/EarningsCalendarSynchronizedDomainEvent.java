package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class EarningsCalendarSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public EarningsCalendarSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.CALENDAR_EARNINGS_SYNCED;
	}
}
