package com.comida.sia.indicators.adapter.messaging.inflation;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("InflationSyncNotificationSubscriber")
public class InflationSyncNotificationSubscriber extends NotificationSubscriber {
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.INFLATION_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException { 
		super.handle(message);
	}
}
