package com.comida.sia.intelligence.news.domain.model;

import com.comida.sia.intelligence.news.port.acquirer.FeedItem;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class NewsFeedSynchronizationAdminAssembler {
	
	public NewsFeedSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed> assemblyNewsFeedSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::new)
				.with(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::setNotifier, notifier)
				.with(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::setDomainEventBuilder, new NewsFeedSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::setTranslator, new NewsFeedTranslator())
				.with(SynchronizationAdmin<FeedItem, NewsFeedTranslator, NewsFeed>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<FeedItem, NewsFeedTranslator, NewsFeed>::new)
							.with(SynchronizationWorker<FeedItem, NewsFeedTranslator, NewsFeed>::setRegister, 
									GenericBuilder.of(Register<FeedItem, NewsFeedTranslator, NewsFeed>::new)
										.with(Register<FeedItem, NewsFeedTranslator, NewsFeed>::setPeriod, syncState.getPeriod())
										.with(Register<FeedItem, NewsFeedTranslator, NewsFeed>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<FeedItem>::new)
													.with(AscendDiscriminator<FeedItem>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
}
