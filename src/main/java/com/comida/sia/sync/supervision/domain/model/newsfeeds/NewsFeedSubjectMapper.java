package com.comida.sia.sync.supervision.domain.model.newsfeeds;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class NewsFeedSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case NEWS_FEED_SYNC_ORDERED:
				return SynchronizationScope.NEWS_FEED;
			case NEWS_FEED_SYNCED:
				return SynchronizationScope.NEWS_FEED;
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
