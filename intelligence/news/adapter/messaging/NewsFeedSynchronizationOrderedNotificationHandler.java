package com.comida.sia.intelligence.news.adapter.messaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.news.port.application.NewsFeedService;
import com.comida.sia.intelligence.news.port.application.RegisterNewsFeedCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.newsfeeds.NewsFeedSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.newsfeeds.NewsFeedSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("NewsFeedSynchronizationOrderedNotificationHandler")
public class NewsFeedSynchronizationOrderedNotificationHandler implements NotificationHandler{

	private NewsFeedSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("NewsFeedApplicationService")
	private NewsFeedService service;
	
	public NewsFeedSynchronizationOrderedNotificationHandler(
			@Qualifier("NewsFeedSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, NewsFeedSynchronizationOrderedNotification.class);
			
			if(Subject.NEWS_FEED_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<NewsFeedService> buildCommand(NewsFeedSynchronizationOrderedDomainEvent domainEvent) {
		return new RegisterNewsFeedCommand(
				service,
				domainEvent.getSyncState());
	}

}
