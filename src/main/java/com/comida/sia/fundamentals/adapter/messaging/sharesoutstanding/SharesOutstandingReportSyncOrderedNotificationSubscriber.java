package com.comida.sia.fundamentals.adapter.messaging.sharesoutstanding;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("SharesOutstandingReportSyncOrderedNotificationSubscriber")
public class SharesOutstandingReportSyncOrderedNotificationSubscriber extends NotificationSubscriber {
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.SHARES_OUTSTANDING_REPORT_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException { 
		super.handle(message);
	}
}
