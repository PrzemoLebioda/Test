package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class ExchangeQuoteInterday30MinSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent{

	public ExchangeQuoteInterday30MinSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_INTERDAY_30_SYNCED;
	}

}
