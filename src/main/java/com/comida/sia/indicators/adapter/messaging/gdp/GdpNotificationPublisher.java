package com.comida.sia.indicators.adapter.messaging.gdp;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("GdpNotificationPublisher")
public class GdpNotificationPublisher extends NotificationPublisher{

	public GdpNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.GDP_EXCHANGE;
	}

}
