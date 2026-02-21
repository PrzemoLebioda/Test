package com.comida.sia.fundamentals.adapter.messaging;

import org.springframework.stereotype.Component;

import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("EarningsCalendarUpdatedNotificationPublisher")
public class EarningsCalendarUpdatedNotificationPublisher extends NotificationPublisher {

	@Override
	protected void configureExchange() {
		//exchangeName =  RabbitmqConfig.EARNINGS_REPORTS_EXCHANGE;
	}

}
