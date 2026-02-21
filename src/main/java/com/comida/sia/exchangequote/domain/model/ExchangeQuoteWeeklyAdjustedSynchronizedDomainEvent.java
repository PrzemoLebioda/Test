package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNCED;
	}

}
