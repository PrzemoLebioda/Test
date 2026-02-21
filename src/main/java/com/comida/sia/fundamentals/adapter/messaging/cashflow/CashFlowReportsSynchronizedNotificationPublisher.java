package com.comida.sia.fundamentals.adapter.messaging.cashflow;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("CashFlowReportsSynchronizedNotificationPublisher")
public class CashFlowReportsSynchronizedNotificationPublisher extends NotificationPublisher {

	public CashFlowReportsSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.CASH_FLOW_REPORTS_EXCHANGE;
	}

}
