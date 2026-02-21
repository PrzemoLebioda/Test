package com.comida.sia.indicators.adapter.messaging.cpi;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("CpiSyncNotificationSubscriber")
public class CpiSyncNotificationSubscriber extends NotificationSubscriber {
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.CPI_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException { 
		super.handle(message);
	}
}
