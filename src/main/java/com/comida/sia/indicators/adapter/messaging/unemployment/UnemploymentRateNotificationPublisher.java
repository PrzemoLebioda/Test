package com.comida.sia.indicators.adapter.messaging.unemployment;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("UnemploymentRateNotificationPublisher")
public class UnemploymentRateNotificationPublisher extends NotificationPublisher {

	public UnemploymentRateNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.UNEMPLOYMENT_RATE_EXCHANGE;
	}

}
