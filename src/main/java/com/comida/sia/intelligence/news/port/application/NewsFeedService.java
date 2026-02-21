package com.comida.sia.intelligence.news.port.application;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface NewsFeedService {
	void registerNewsFeed(SynchronizationStateDto syncState);
}
