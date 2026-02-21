package com.comida.sia.fundamentals.adapter.messaging.corpoevents.earnings;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.corpoevents.earnings.EarningsCalendarService;
import com.comida.sia.fundamentals.port.application.corpoevents.earnings.SynchronizeCorporateEarningEventCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.adapter.messaging.calendar.EarningsCalendarSynchronizationOrderedNotification;
import com.comida.sia.sync.supervision.domain.model.calendar.EarningsCalendarSynchronizationOrderedDomainEvent;
import com.opencsv.exceptions.CsvException;

@Component("CalendarSynchronizationOrderedNotificationHandler")
public class EarningsCalendarSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private EarningsCalendarSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("EarningsCalendarApplicationService")
	private EarningsCalendarService service;
	
	public EarningsCalendarSynchronizationOrderedNotificationHandler(
			@Qualifier("EarningsCalendarNotificationsSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningsCalendarSynchronizationOrderedNotification.class);
			
			if(Subject.CALENDAR_EARNINGS_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<EarningsCalendarService> buildCommand(EarningsCalendarSynchronizationOrderedDomainEvent domainEvent){
		return new SynchronizeCorporateEarningEventCommand(service, domainEvent.getSyncState());
	}
}
