package com.comida.sia.sync.supervision.adapter.messaging.company;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("SynchronizationOrderNotificationPublisher")
public class CompanySynchronizationNotificationPublisher extends NotificationPublisher {
	
	public CompanySynchronizationNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.COMPANY_SYNC_EXCHANGE;
	}
}
