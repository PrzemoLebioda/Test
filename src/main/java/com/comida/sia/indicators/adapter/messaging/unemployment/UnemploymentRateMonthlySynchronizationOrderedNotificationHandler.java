package com.comida.sia.indicators.adapter.messaging.unemployment;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.unemployment.UnemploymentRateService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.UnemploymentRateMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.UnemploymentRateMonthlySynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.unemployment.SynchronizeMonthlyUnemploymentRateCommand;

@Component("UnemploymentRateMonthlySynchronizationOrderedNotificationHandler")
public class UnemploymentRateMonthlySynchronizationOrderedNotificationHandler implements NotificationHandler {

	private UnemploymentRateMonthlySynchronizationOrderedNotification notification;

	@Autowired
	@Qualifier("UnemploymentRateApplicationService")
	private UnemploymentRateService service;
	
	public UnemploymentRateMonthlySynchronizationOrderedNotificationHandler(
			@Qualifier("UnemploymentRateNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, UnemploymentRateMonthlySynchronizationOrderedNotification.class);
			
			if(Subject.UNEMPLOYMENT_RATE_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<UnemploymentRateService> buildCommand(UnemploymentRateMonthlySynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeMonthlyUnemploymentRateCommand(
				service,
				domainEvent.getSyncState());
	}
}
