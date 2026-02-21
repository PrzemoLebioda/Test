package com.comida.sia.intelligence.news.port.messaging;

import java.util.UUID;

import com.comida.sia.intelligence.news.domain.model.NewsFeedSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class NewsFeedSynchronizedNotification extends Notification<NewsFeedSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2133160292054256447L;

	public NewsFeedSynchronizedNotification(UUID id, NewsFeedSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
