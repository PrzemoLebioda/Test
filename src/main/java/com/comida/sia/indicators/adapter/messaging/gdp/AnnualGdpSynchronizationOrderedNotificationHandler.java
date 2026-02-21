package com.comida.sia.indicators.adapter.messaging.gdp;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.gdp.AnnualGdpSynchronizeCommand;
import com.comida.sia.indicators.port.application.gdp.GdpService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.AnnualGdpSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.AnnualGdpSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("AnnualGdpSynchronizationOrderedNotificationHandler")
public class AnnualGdpSynchronizationOrderedNotificationHandler implements NotificationHandler{

	private AnnualGdpSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("GdpApplicationService")
	private GdpService service;
	
	public AnnualGdpSynchronizationOrderedNotificationHandler(
			@Qualifier("GdpSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, AnnualGdpSynchronizationOrderedNotification.class);
			
			if(Subject.GDP_ANNUAL_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<GdpService> buildCommand(AnnualGdpSynchronizationOrderedDomainEvent domainEvent) {
		return new AnnualGdpSynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}

}
