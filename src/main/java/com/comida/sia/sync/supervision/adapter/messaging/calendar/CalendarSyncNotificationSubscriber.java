package com.comida.sia.sync.supervision.adapter.messaging.calendar;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("CalendarSyncNotificationSubscriber")
public class CalendarSyncNotificationSubscriber extends NotificationSubscriber {

	public CalendarSyncNotificationSubscriber() {
		super();
	}
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.CALENDAR_SYNC_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}
}
