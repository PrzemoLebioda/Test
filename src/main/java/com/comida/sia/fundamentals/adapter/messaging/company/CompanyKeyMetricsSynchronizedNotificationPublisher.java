package com.comida.sia.fundamentals.adapter.messaging.company;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CompanyKeyMetricsSynchronizedNotificationPublisher")
public class CompanyKeyMetricsSynchronizedNotificationPublisher extends NotificationPublisher{

	public CompanyKeyMetricsSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.KEY_METRICS_EXCHANGE;
	}

}
