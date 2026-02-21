package com.comida.sia.sync.supervision.adapter.messaging.newsfeeds;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("NewsFeedSynchronizationPublisher")
public class NewsFeedSynchronizationPublisher extends NotificationPublisher {

	public NewsFeedSynchronizationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.NEWS_SYNC_EXCHANGE;
	}

}
