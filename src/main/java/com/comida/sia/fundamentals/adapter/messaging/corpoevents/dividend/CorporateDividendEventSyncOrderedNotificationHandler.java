package com.comida.sia.fundamentals.adapter.messaging.corpoevents.dividend;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.corpoevents.dividend.CorporateDividendEventService;
import com.comida.sia.fundamentals.port.application.corpoevents.dividend.SynchronizeCorporateDividendEventCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarDividendSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.CalendarDividendSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("CorporateDividendEventSyncOrderedNotificationHandler")
public class CorporateDividendEventSyncOrderedNotificationHandler implements NotificationHandler {

	private CalendarDividendSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("CorporateDividendEventApplicationService")
	private CorporateDividendEventService service;
	
	public CorporateDividendEventSyncOrderedNotificationHandler(
			@Qualifier("CorporateDividendEventSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
		
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, CalendarDividendSyncOrderedNotification.class);
			
			if(Subject.CALENDAR_DIVIDEND_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<CorporateDividendEventService> buildCommand(CalendarDividendSyncOrderedDomainEvent domainEvent){
		return new SynchronizeCorporateDividendEventCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
}
