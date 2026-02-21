package com.comida.sia.sync.supervision.port.application.newsfeeds;

import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public interface NewsFeedSynchronizationSupervisorService {
	public void orderSynchronization();
	public void calculateCurrentSyncState(SynchronizationSummary summary);
}
