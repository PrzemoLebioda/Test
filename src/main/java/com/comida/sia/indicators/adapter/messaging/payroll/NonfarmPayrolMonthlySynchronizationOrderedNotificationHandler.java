package com.comida.sia.indicators.adapter.messaging.payroll;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.payroll.NonfarmPayrollMonthlySynchronizeCommand;
import com.comida.sia.indicators.port.application.payroll.NonfarmPayrollService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.NonfarmPayrolMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.NonfarmPayrolMonthlySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("NonfarmPayrolMonthlySynchronizationOrderedNotificationHandler")
public class NonfarmPayrolMonthlySynchronizationOrderedNotificationHandler implements NotificationHandler {
	
	private NonfarmPayrolMonthlySynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("NonfarmPayrollApplicationService")
	private NonfarmPayrollService service;
	
	public NonfarmPayrolMonthlySynchronizationOrderedNotificationHandler(
			@Qualifier("NonfarmPayrollNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, NonfarmPayrolMonthlySynchronizationOrderedNotification.class);
			
			if(Subject.NONFARM_PAYROLL_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<NonfarmPayrollService> buildCommand(NonfarmPayrolMonthlySynchronizationOrderedDomainEvent domainEvent) {
		return new NonfarmPayrollMonthlySynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}
}
