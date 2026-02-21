package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("AdjustedExchangeQuoteNotificationSubscriber")
public class AdjustedExchangeQuoteNotificationSubscriber extends NotificationSubscriber {

	@Override
	@RabbitListener(queues = RabbitmqConfig.QUOTATION_ADJUSTED_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}
	
}
