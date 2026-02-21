 package com.comida.sia.sync.supervision.adapter.messaging.calendar;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.EarningsCalendarSynchronizedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.corpoevents.earnings.EarningsCalendarSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.calendar.CalendarSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.sync.supervision.port.application.calendar.CalculateCurrentCalendarSynchronizationStateCommand;

@Component("EarningsCalendarSynchronizedNotificationHandler")
public class EarningsCalendarSynchronizedNotificationHandler implements NotificationHandler {

	private EarningsCalendarSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("CalendarSynchronizationSupervisorApplicationService")
	private CalendarSynchronizationSupervisorService calendarSynchronizationSupervisorService;
	
	public EarningsCalendarSynchronizedNotificationHandler(
			@Qualifier("CalendarSyncNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningsCalendarSynchronizedNotification.class);
			
			if(Subject.CALENDAR_EARNINGS_SYNCED.equals(notification.getSubject())) {
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
	
	private Command<CalendarSynchronizationSupervisorService> buildCommand(EarningsCalendarSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentCalendarSynchronizationStateCommand(
						calendarSynchronizationSupervisorService,
						domainEvent.getSummary());
	}

}
