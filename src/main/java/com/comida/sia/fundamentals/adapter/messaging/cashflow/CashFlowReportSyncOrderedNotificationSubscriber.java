package com.comida.sia.fundamentals.adapter.messaging.cashflow;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.opencsv.exceptions.CsvException;

@Component("CashFlowReportSyncOrderedNotificationSubscriber")
public class CashFlowReportSyncOrderedNotificationSubscriber extends NotificationSubscriber{

	public CashFlowReportSyncOrderedNotificationSubscriber(){
		super();
	}
	
	@Override
	@RabbitListener(queues = RabbitmqConfig.CASH_FLOW_REPORTS_QUEUE)
	public void handle(String message) throws ParseException, IOException, CsvException {
		super.handle(message);
	}
}
