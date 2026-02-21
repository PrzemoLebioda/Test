package com.comida.sia.sync.supervision.port.repository;

import com.comida.sia.sync.supervision.domain.model.newsfeeds.NewsFeedSynchronizationSupervisor;

public interface NewsFeedSynchronizationSypervisorRepository {
	void store(NewsFeedSynchronizationSupervisor supervisor);
	void update(NewsFeedSynchronizationSupervisor supervisor);
	
	NewsFeedSynchronizationSupervisor get();
}
