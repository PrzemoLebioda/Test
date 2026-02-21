package com.comida.sia.sync.supervision.adapter.messaging.newsfeeds;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.news.domain.model.NewsFeedSynchronizedDomainEvent;
import com.comida.sia.intelligence.news.port.messaging.NewsFeedSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.newsfeeds.CalculateCurrentNewsFeedSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.newsfeeds.NewsFeedSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("NewsFeedSynchronizedNotificationHandler")
public class NewsFeedSynchronizedNotificationHandler implements NotificationHandler {

	private NewsFeedSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("NewsFeedSynchronizationSupervisorApplicationService")
	private NewsFeedSynchronizationSupervisorService service;
	
	public NewsFeedSynchronizedNotificationHandler(
			@Qualifier("NewsFeedSyncNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, NewsFeedSynchronizedNotification.class);
			
			if(Subject.NEWS_FEED_SYNCED.equals(notification.getSubject())) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public void hanle() throws ParseException, IOException, CsvException {
		buildCommand(notification.getPayload()).execute();
	}
	
	private Command<NewsFeedSynchronizationSupervisorService> buildCommand(NewsFeedSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentNewsFeedSynchronizationStateCommand(
				service,
				domainEvent.getSummary());
	}

}
