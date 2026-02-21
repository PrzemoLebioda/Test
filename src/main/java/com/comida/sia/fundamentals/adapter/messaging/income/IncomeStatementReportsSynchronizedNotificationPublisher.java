package com.comida.sia.fundamentals.adapter.messaging.income;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("IncomeStatementReportsSynchronizedNotificationPublisher")
public class IncomeStatementReportsSynchronizedNotificationPublisher  extends NotificationPublisher {

	public IncomeStatementReportsSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.INCOME_STATEMENT_REPORTS_EXCHANGE;
	}

}
