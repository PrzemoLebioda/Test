package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("IndicatorsSynchronizationPublisher")
public class IndicatorsSynchronizationPublisher extends NotificationPublisher {

	public IndicatorsSynchronizationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.INDICATORS_SYNC_EXCHANGE;
	}

}
