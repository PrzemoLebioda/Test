package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNCED;
	}

}
