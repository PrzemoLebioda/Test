package com.comida.sia.indicators.adapter.messaging.intrestrate;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.intrestrate.IntrestRateService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.IntrestRateMonthlySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.intrestrate.SynchronizeMonthlyIntrestRateCommand;

@Component("IntrestRateMonthlySynchronizationOrderedNotificationHandler")
public class IntrestRateMonthlySynchronizationOrderedNotificationHandler implements NotificationHandler {

	private IntrestRateMonthlySynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("IntrestRateApplicationService")
	private IntrestRateService service;
	
	public IntrestRateMonthlySynchronizationOrderedNotificationHandler(
			@Qualifier("IntrestRateNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IntrestRateMonthlySynchronizationOrderedNotification.class);
			
			if(Subject.INTREST_RATE_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<IntrestRateService> buildCommand(IntrestRateMonthlySynchronizationOrderedDomainEvent domainEvent) {
		// TODO Auto-generated method stub
		return new SynchronizeMonthlyIntrestRateCommand(
				service,
				domainEvent.getSyncState());
	}

}
