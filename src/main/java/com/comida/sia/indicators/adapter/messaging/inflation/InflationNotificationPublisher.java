package com.comida.sia.indicators.adapter.messaging.inflation;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("InflationNotificationPublisher")
public class InflationNotificationPublisher extends NotificationPublisher{

	public InflationNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.INFLATION_EXCHANGE;
	}

}
