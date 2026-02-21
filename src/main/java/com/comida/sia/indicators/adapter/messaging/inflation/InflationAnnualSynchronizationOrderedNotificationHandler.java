package com.comida.sia.indicators.adapter.messaging.inflation;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.inflation.InflationService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.InflationAnnualSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.InflationAnnualSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.inflation.AnnualInflationSynchronizeCommand;

@Component("InflationAnnualSynchronizationOrderedNotificationHandler")
public class InflationAnnualSynchronizationOrderedNotificationHandler implements NotificationHandler {
	
	private InflationAnnualSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("InflationApplicationService")
	private InflationService service;

	public InflationAnnualSynchronizationOrderedNotificationHandler(
			@Qualifier("InflationSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, InflationAnnualSynchronizationOrderedNotification.class);
			
			if(Subject.INFLATION_ANNUAL_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<InflationService> buildCommand(InflationAnnualSynchronizationOrderedDomainEvent domainEvent) {
		return new AnnualInflationSynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}
}
