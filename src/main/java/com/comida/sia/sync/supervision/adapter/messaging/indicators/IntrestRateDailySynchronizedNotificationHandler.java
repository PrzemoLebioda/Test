package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.intrestrate.IntrestRateDailySynchronizedDomainEvent;
import com.comida.sia.indicators.port.messaging.intrestrate.IntrestRateDailySynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.indicators.CalculateCurrentIndicatorsSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.indicators.IndicatorsSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("IntrestRateDailySynchronizedNotificationHandler")
public class IntrestRateDailySynchronizedNotificationHandler implements NotificationHandler {

	private IntrestRateDailySynchronizedNotification notification;
	
	@Autowired
	@Qualifier("IndicatorsSynchronizationSupervisorApplicationService")
	private IndicatorsSynchronizationSupervisorService service;
	
	public IntrestRateDailySynchronizedNotificationHandler(
			@Qualifier("IndicatorsNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IntrestRateDailySynchronizedNotification.class);
			
			if(Subject.INTREST_RATE_DAILY_SYNCED.equals(notification.getSubject())) {
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

	private Command<IndicatorsSynchronizationSupervisorService> buildCommand(IntrestRateDailySynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentIndicatorsSynchronizationStateCommand(
				service,
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}

}
