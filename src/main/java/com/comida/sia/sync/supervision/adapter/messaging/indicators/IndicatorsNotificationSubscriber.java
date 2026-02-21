package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("IndicatorsNotificationSubscriber")
public class IndicatorsNotificationSubscriber extends NotificationSubscriber{
	
	public IndicatorsNotificationSubscriber() {
		super();
	}

	@Override
	@RabbitListener(queues = RabbitmqConfig.INDICATORS_SYNC_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}
}
