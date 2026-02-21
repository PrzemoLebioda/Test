package com.comida.sia.fundamentals.adapter.messaging.corpoevents.dividend;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CorporateDividendEventSynchronizedNotificationPublisher")
public class CorporateDividendEventSynchronizedNotificationPublisher extends NotificationPublisher {

	public CorporateDividendEventSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.CORPORATE_DIVIDEND_EVENT_EXCHANGE;
	}

}
