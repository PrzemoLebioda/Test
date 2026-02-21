package com.comida.sia.exchangequote.adapter.rmessaging;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("AdjustedExchangeQuoteNotificationPublisher")
public class AdjustedExchangeQuoteNotificationPublisher extends NotificationPublisher {

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.QUOTATION_ADJUSTED_EXCHANGE;
	}

}
