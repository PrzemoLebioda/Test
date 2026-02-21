package com.comida.sia.options.adapter.messaging;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("OptionsNotificationPublisher")
public class OptionsNotificationPublisher extends NotificationPublisher{

	public OptionsNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.OPTIONS_EXCHANGE;
	}

}
