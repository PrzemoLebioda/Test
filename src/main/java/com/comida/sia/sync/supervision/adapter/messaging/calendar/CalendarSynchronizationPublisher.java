package com.comida.sia.sync.supervision.adapter.messaging.calendar;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CalendarSynchronizationPublisher")
public class CalendarSynchronizationPublisher extends NotificationPublisher {

	public CalendarSynchronizationPublisher() {
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.CALENDAR_SYNC_EXCHANGE;
	}

}
