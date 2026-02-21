package com.comida.sia.indicators.adapter.messaging.payroll;

import org.springframework.stereotype.Component;

import com.comida.sia.config.RabbitmqConfig;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;

@Component("NonfarmPayrollNotificationPublisher")
public class NonfarmPayrollNotificationPublisher extends NotificationPublisher {

	public NonfarmPayrollNotificationPublisher() {
		super();
	}
	
	@Override
	protected void configureExchange() {
		exchangeName =  RabbitmqConfig.NONFARM_PAYROLL_EXCHANGE;
	}

}
