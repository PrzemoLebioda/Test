package com.comida.sia.fundamentals.adapter.messaging.stock;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("StockNotificationPublisher")
public class StockNotificationPublisher extends NotificationPublisher{
	
	public StockNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.STOCK_LISTING_STATUS_EXCHANGE;
		
	}

}
