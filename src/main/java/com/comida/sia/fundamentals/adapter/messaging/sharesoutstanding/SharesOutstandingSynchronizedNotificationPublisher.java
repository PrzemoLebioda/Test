package com.comida.sia.fundamentals.adapter.messaging.sharesoutstanding;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("SharesOutstandingSynchronizedNotificationPublisher")
public class SharesOutstandingSynchronizedNotificationPublisher extends NotificationPublisher {

	public SharesOutstandingSynchronizedNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.SHARES_OUTSTANDING_REPORT_EXCHANGE;
	}

}
