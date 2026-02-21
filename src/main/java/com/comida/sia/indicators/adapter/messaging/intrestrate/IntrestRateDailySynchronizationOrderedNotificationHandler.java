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
import com.comida.sia.sync.supervision.domain.model.indicators.events.IntrestRateDailySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.IntrestRateDailySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.intrestrate.SynchronizeDailyIntrestRateCommand;

@Component("IntrestRateDailySynchronizationOrderedNotificationHandler")
public class IntrestRateDailySynchronizationOrderedNotificationHandler implements NotificationHandler {

	@Autowired
	@Qualifier("IntrestRateApplicationService")
	private IntrestRateService service;
	
	private IntrestRateDailySynchronizationOrderedNotification notification;
	
	public IntrestRateDailySynchronizationOrderedNotificationHandler(
			@Qualifier("IntrestRateNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IntrestRateDailySynchronizationOrderedNotification.class);
			
			if(Subject.INTREST_RATE_DAILY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<IntrestRateService> buildCommand(IntrestRateDailySynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeDailyIntrestRateCommand(
				service,
				domainEvent.getSyncState());
	}

}
