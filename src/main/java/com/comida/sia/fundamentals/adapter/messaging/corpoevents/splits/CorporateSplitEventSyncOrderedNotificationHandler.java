package com.comida.sia.fundamentals.adapter.messaging.corpoevents.splits;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.corpoevents.splits.CorporateSplitEventService;
import com.comida.sia.fundamentals.port.application.corpoevents.splits.SynchronizeCorporateSplitEventCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarSplitsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.CalendarSplitsSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("CorporateSplitEventSyncOrderedNotificationHandler")
public class CorporateSplitEventSyncOrderedNotificationHandler implements NotificationHandler{

	private CalendarSplitsSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("CorporateSplitEventApplicationService")
	private CorporateSplitEventService service;
	
	public CorporateSplitEventSyncOrderedNotificationHandler(
			@Qualifier("CorporateSplitEventSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, CalendarSplitsSyncOrderedNotification.class);
			
			if(Subject.CALENDAR_SPLITS_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<CorporateSplitEventService> buildCommand(CalendarSplitsSyncOrderedDomainEvent domainEvent){
		return new SynchronizeCorporateSplitEventCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
}
