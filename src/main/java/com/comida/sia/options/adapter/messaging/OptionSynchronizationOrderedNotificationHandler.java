package com.comida.sia.options.adapter.messaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.options.port.application.OptionService;
import  com.comida.sia.options.port.application.SynchronizeOptionsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.OptionsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.OptionsSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("OptionSynchronizationOrderedNotificationHandler")
public class OptionSynchronizationOrderedNotificationHandler implements NotificationHandler {
	
	private OptionsSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("OptionApplicationService")
	private OptionService service;
	
	public OptionSynchronizationOrderedNotificationHandler(
			@Qualifier("OptionsNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, OptionsSyncOrderedNotification.class);
			
			if(Subject.OPTIONS_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<OptionService> buildCommand(OptionsSyncOrderedDomainEvent domainEvent) {
		return new SynchronizeOptionsCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
