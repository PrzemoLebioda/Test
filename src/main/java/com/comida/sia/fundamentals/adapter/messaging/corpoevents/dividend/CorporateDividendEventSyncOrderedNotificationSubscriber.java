package com.comida.sia.fundamentals.adapter.messaging.corpoevents.dividend;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("CorporateDividendEventSyncOrderedNotificationSubscriber")
public class CorporateDividendEventSyncOrderedNotificationSubscriber extends NotificationSubscriber{

	@Override
	@RabbitListener(queues = RabbitmqConfig.CORPORATE_DIVIDEND_EVENT_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException { 
		super.handle(message);
	}
	
}
