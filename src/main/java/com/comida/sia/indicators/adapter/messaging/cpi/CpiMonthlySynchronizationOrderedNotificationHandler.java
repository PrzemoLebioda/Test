package com.comida.sia.indicators.adapter.messaging.cpi;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.cpi.CpiMonthlySynchronizeCommand;
import com.comida.sia.indicators.port.application.cpi.CpiService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.CpiMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.CpiMonthlySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("CpiMonthlySynchronizationOrderedNotificationHandler")
public class CpiMonthlySynchronizationOrderedNotificationHandler implements NotificationHandler {

	private CpiMonthlySynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("CpiApplicationService")
	private CpiService service;
	
	public CpiMonthlySynchronizationOrderedNotificationHandler(
			@Qualifier("CpiSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, CpiMonthlySynchronizationOrderedNotification.class);
			
			if(Subject.CPI_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<CpiService> buildCommand(CpiMonthlySynchronizationOrderedDomainEvent domainEvent) {
		return new CpiMonthlySynchronizeCommand(
				service,
				domainEvent.getSyncState());
	}

}
