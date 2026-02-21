package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("ExchangeQuoteSynchronizationNotificationPublisher")
public class ExchangeQuoteSynchronizationNotificationPublisher extends NotificationPublisher{

	public ExchangeQuoteSynchronizationNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.QUOTATIONS_SYNC_EXCHANGE;
	}

}
