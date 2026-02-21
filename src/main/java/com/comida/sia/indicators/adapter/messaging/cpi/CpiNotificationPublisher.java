package com.comida.sia.indicators.adapter.messaging.cpi;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CpiNotificationPublisher")
public class CpiNotificationPublisher extends NotificationPublisher{

	public CpiNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.CPI_EXCHANGE;
	}

}
