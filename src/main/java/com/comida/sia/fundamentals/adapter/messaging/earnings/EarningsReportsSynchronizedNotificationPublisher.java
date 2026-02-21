package com.comida.sia.fundamentals.adapter.messaging.earnings;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("EarningsReportsSynchronizedNotificationPublisher")
public class EarningsReportsSynchronizedNotificationPublisher extends NotificationPublisher{

	public EarningsReportsSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.EARNINGS_REPORTS_EXCHANGE;
	}

}
