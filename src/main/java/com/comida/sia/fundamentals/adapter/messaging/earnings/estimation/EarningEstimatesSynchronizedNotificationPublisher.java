package com.comida.sia.fundamentals.adapter.messaging.earnings.estimation;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("EarningEstimatesSynchronizedNotificationPublisher")
public class EarningEstimatesSynchronizedNotificationPublisher extends NotificationPublisher {
	
	public EarningEstimatesSynchronizedNotificationPublisher() {
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.EARNING_ESTIMATES_REPORT_EXCHANGE;
	}

}
