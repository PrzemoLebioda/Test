package com.comida.sia.intelligence.news.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.news.domain.model.NewsFeed;
import com.comida.sia.intelligence.news.domain.model.NewsFeedSynchronizationAdminAssembler;
import com.comida.sia.intelligence.news.port.acquirer.NewsDataAcquirer;
import com.comida.sia.intelligence.news.port.application.NewsFeedService;
import com.comida.sia.intelligence.news.port.repository.NewsRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("NewsFeedApplicationService")
public class NewsFeedApplicationService implements NewsFeedService, Notifier{
	
	@Autowired
	@Qualifier("NewsHibernateRepository")
	private NewsRepository repository;
	
	@Autowired
	@Qualifier("AlfavantageNewsDataAcquirer")
	private NewsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("NewsNotificationPublisher")
	private NotificationPublisher publisher;

	@Override
	@Transactional
	public void registerNewsFeed(SynchronizationStateDto syncState) {
		try {
			NewsFeedSynchronizationAdminAssembler assembler = new NewsFeedSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyNewsFeedSyncAdmin(syncState, this)
					.synchronize(
							acquirer.gatherNews(syncState.getLastSyncedEventOccuranceTime()).getFeed(), 
							syncState));
			
		} catch (EmptyListException e){
			log.warn("News feeds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<NewsFeed> newsFeeds) {
		for(NewsFeed newsFeed : newsFeeds) {
			repository.store(newsFeed);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
