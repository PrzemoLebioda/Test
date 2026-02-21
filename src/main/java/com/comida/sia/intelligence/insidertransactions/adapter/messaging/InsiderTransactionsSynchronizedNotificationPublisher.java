package com.comida.sia.intelligence.insidertransactions.adapter.messaging;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("InsiderTransactionsynchronizedNotificationPublisher")
public class InsiderTransactionsSynchronizedNotificationPublisher extends NotificationPublisher {

	public InsiderTransactionsSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.INSIDERS_TRANSACTIONS_EXCHANGE;
	}

}
