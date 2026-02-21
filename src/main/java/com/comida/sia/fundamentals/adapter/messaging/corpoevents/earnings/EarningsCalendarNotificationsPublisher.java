package com.comida.sia.fundamentals.adapter.messaging.corpoevents.earnings;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("EarningsCalendarNotificationsPublisher")
public class EarningsCalendarNotificationsPublisher extends NotificationPublisher{

	public EarningsCalendarNotificationsPublisher() {
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName = RabbitmqConfig.EARNING_CALENDAR_EXCHANGE;
	}

}
