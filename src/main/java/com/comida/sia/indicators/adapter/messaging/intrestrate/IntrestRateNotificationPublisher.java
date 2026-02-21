package com.comida.sia.indicators.adapter.messaging.intrestrate;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("IntrestRateNotificationPublisher")
public class IntrestRateNotificationPublisher extends NotificationPublisher {

	public IntrestRateNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.INTREST_RATE_EXCHANGE;
	}

}
