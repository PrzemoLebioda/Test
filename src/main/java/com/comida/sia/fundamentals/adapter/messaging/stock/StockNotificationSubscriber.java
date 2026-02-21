package com.comida.sia.fundamentals.adapter.messaging.stock;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("StockNotificationSubscriber")
public class StockNotificationSubscriber extends NotificationSubscriber {

	public StockNotificationSubscriber() {
		super();
	}
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.STOCK_LISTING_STATUS_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}
}
