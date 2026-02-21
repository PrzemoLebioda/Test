package com.comida.sia.sync.supervision.adapter.messaging.stock;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("StockSynchronizationPublisher")
public class StockSynchronizationPublisher extends NotificationPublisher  {
	
	public StockSynchronizationPublisher() {
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.STOCK_SYNC_EXCHANGE;
		
	}
}
