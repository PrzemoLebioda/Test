package com.comida.sia.indicators.adapter.messaging.treasuryyeald;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("TreasuryYealdNotificationPublisher")
public class TreasuryYealdNotificationPublisher extends NotificationPublisher {

	public TreasuryYealdNotificationPublisher() {
		super();
	}

	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.TREASURY_YEALD_EXCHANGE;
	}
}
