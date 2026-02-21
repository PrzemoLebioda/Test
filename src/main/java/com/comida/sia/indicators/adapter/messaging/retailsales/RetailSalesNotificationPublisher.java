package com.comida.sia.indicators.adapter.messaging.retailsales;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("RetailSalesNotificationPublisher")
public class RetailSalesNotificationPublisher extends NotificationPublisher {

	public RetailSalesNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.RETAIL_SALES_EXCHANGE;
	}

}
