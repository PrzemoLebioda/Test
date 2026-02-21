package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.payroll.NonfarmPayrollMonthlySynchronizedDomainEvent;
import com.comida.sia.indicators.port.messaging.payroll.NonfarmPayrollMonthlySynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.indicators.CalculateCurrentIndicatorsSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.indicators.IndicatorsSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("NonfarmPayrollMonthlySynchronizedNotificationHandler")
public class NonfarmPayrollMonthlySynchronizedNotificationHandler implements NotificationHandler {

	private NonfarmPayrollMonthlySynchronizedNotification notification;

	@Autowired
	@Qualifier("IndicatorsSynchronizationSupervisorApplicationService")
	private IndicatorsSynchronizationSupervisorService service;
	
	public NonfarmPayrollMonthlySynchronizedNotificationHandler(
			@Qualifier("IndicatorsNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, NonfarmPayrollMonthlySynchronizedNotification.class);
			
			if(Subject.NONFARM_PAYROLL_RATE_MONTHLY_SYNCED.equals(notification.getSubject())) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public void hanle() throws ParseException, IOException, CsvException {
		buildCommand(notification.getPayload()).execute();
	}

	private Command<IndicatorsSynchronizationSupervisorService> buildCommand(NonfarmPayrollMonthlySynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentIndicatorsSynchronizationStateCommand(
				service,
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}
}
