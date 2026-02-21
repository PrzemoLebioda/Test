package com.comida.sia.indicators.adapter.messaging.gdp;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.gdp.GdpService;
import com.comida.sia.indicators.port.application.gdp.QuarterGdpPerCapitaSynchronizeCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.QuarterlyGdpPerCapitaSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("QuarterlyGdpPerCapitaSynchronizationOrderedNotificationHandler")
public class QuarterlyGdpPerCapitaSynchronizationOrderedNotificationHandler implements NotificationHandler{

	private QuarterlyGdpPerCapitaSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("GdpPerCapitaApplicationService")
	private GdpService service;
	
	public QuarterlyGdpPerCapitaSynchronizationOrderedNotificationHandler(
			@Qualifier("GdpSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, QuarterlyGdpPerCapitaSynchronizationOrderedNotification.class);
			
			if(Subject.GDP_ANNUAL_PER_CAPITA_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<GdpService> buildCommand(QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent domainEvent) {
		return new QuarterGdpPerCapitaSynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}

}
