package com.comida.sia.fundamentals.adapter.messaging.earnings.estimation;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("EarningEstimatesReportSyncOrderedNotificationSubscriber")
public class EarningEstimatesReportSyncOrderedNotificationSubscriber extends NotificationSubscriber {

	@Override
	@RabbitListener(queues = RabbitmqConfig.EARNING_ESTIMATES_REPORT_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException { 
		super.handle(message);
	}

}
