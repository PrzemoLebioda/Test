package com.comida.sia.fundamentals.adapter.messaging.balancesheet;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("BalanceSheetReportsSynchronizedNotificationPublisher")
public class BalanceSheetReportsSynchronizedNotificationPublisher extends NotificationPublisher{

	public BalanceSheetReportsSynchronizedNotificationPublisher(){
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.BALANCE_SHEET_REPORTS_EXCHANGE;
	}
}
