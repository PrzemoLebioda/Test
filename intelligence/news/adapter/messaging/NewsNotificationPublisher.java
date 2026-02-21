package com.comida.sia.intelligence.news.adapter.messaging;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("NewsNotificationPublisher")
public class NewsNotificationPublisher extends NotificationPublisher{

	public NewsNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.NEWS_EXCHANGE;
	}

}
