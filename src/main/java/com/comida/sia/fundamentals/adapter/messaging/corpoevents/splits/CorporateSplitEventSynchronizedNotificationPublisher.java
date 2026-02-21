package com.comida.sia.fundamentals.adapter.messaging.corpoevents.splits;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CorporateSplitEventSynchronizedNotificationPublisher")
public class CorporateSplitEventSynchronizedNotificationPublisher extends NotificationPublisher {

	public CorporateSplitEventSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.CORPORATE_SPLIT_EVENT_EXCHANGE;
	}

}
