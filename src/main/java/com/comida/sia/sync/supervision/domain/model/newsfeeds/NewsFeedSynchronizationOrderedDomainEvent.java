package com.comida.sia.sync.supervision.domain.model.newsfeeds;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class NewsFeedSynchronizationOrderedDomainEvent extends SyncDomainEvent{

	public NewsFeedSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.NEWS_FEED_SYNC_ORDERED;
	}
}
