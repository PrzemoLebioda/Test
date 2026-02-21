package com.comida.sia.indicators.adapter.messaging.durables;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("DurableGoodsOrdersNotificationPublisher")
public class DurableGoodsOrdersNotificationPublisher extends NotificationPublisher {

	public DurableGoodsOrdersNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.DURABLE_GOODS_ORDERS_EXCHANGE;
	}

}
