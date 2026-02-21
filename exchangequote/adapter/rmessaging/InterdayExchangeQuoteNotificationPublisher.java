package com.comida.sia.exchangequote.adapter.rmessaging;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("InterdayExchangeQuoteNotificationPublisher")
public class InterdayExchangeQuoteNotificationPublisher extends NotificationPublisher {

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.QUOTATION_INTERDAY_EXCHANGE;
	}

}
