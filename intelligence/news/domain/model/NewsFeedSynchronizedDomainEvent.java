package com.comida.sia.intelligence.news.domain.model;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class NewsFeedSynchronizedDomainEvent extends CompanyDataSynchronizedDomainEvent {

	public NewsFeedSynchronizedDomainEvent(String tickerSymbol, SynchronizationSummary summary) {
		super(tickerSymbol, summary);
	}

	@Override
	public Subject getSubject() {
		return Subject.NEWS_FEED_SYNCED;
	}

}
