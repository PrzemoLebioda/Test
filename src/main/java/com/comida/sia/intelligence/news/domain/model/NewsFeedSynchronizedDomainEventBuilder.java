package com.comida.sia.intelligence.news.domain.model;

import com.comida.sia.intelligence.news.port.acquirer.FeedItem;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class NewsFeedSynchronizedDomainEventBuilder implements DomainEventBuilder<FeedItem, NewsFeed> {

	public NewsFeedSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<FeedItem, NewsFeed> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build stock synchronized domain event");
		
		return new NewsFeedSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
