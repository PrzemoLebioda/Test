package com.comida.sia.sync.supervision.port.messaging.newsfeeds;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.newsfeeds.NewsFeedSynchronizationOrderedDomainEvent;

public class NewsFeedSynchronizationOrderedNotification extends Notification<NewsFeedSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8438062359833144442L;

	public NewsFeedSynchronizationOrderedNotification(UUID id, NewsFeedSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
