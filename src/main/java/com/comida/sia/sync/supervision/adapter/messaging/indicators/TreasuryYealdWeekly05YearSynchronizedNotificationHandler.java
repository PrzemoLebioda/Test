package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly05YearSynchronizedDomainEvent;
import com.comida.sia.indicators.port.messaging.treasuryyeald.TreasuryYealdWeekly05YearSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.indicators.CalculateCurrentIndicatorsSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.indicators.IndicatorsSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("TreasuryYealdWeekly05YearSynchronizedNotificationHandler")
public class TreasuryYealdWeekly05YearSynchronizedNotificationHandler implements NotificationHandler{

	private TreasuryYealdWeekly05YearSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("IndicatorsSynchronizationSupervisorApplicationService")
	private IndicatorsSynchronizationSupervisorService service;
	
	public TreasuryYealdWeekly05YearSynchronizedNotificationHandler(
			@Qualifier("IndicatorsNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, TreasuryYealdWeekly05YearSynchronizedNotification.class);
			
			if(Subject.TREASURY_YEALD_WEEKLY_05_YEAR_SYNCED.equals(notification.getSubject())) {
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

	private Command<IndicatorsSynchronizationSupervisorService>  buildCommand(TreasuryYealdWeekly05YearSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentIndicatorsSynchronizationStateCommand(
				service,
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}

}
