package com.comida.sia.fundamentals.adapter.messaging.income;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("IncomeStatementReportSyncOrderedNotificationSubscriber")
public class IncomeStatementReportSyncOrderedNotificationSubscriber extends NotificationSubscriber {

	@Override
	@RabbitListener(queues = RabbitmqConfig.INCOME_STATEMENT_REPORTS_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}	
}
