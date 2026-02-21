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
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateWeeklySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.IntrestRateWeeklySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.intrestrate.SynchronizeWeeklyIntrestRateCommand;

@Component("IntrestRateWeeklySynchronizationOrderedNotificationHandler")
public class IntrestRateWeeklySynchronizationOrderedNotificationHandler implements NotificationHandler {

	private IntrestRateWeeklySynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("IntrestRateApplicationService")
	private IntrestRateService service;
	
	public IntrestRateWeeklySynchronizationOrderedNotificationHandler(
			@Qualifier("IntrestRateNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IntrestRateWeeklySynchronizationOrderedNotification.class);
			
			if(Subject.INTREST_RATE_WEEKLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<IntrestRateService> buildCommand(IntrestRateWeeklySynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeWeeklyIntrestRateCommand(
				service,
				domainEvent.getSyncState());
	}

}
