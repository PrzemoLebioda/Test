package com.comida.sia.sync.supervision.adapter.messaging.indicators;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly10YearSynchronizedDomainEvent;
import com.comida.sia.indicators.port.messaging.treasuryyeald.TreasuryYealdMonthly10YearSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.indicators.CalculateCurrentIndicatorsSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.indicators.IndicatorsSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("TreasuryYealdMonthly10YearSynchronizedNotificationHandler")
public class TreasuryYealdMonthly10YearSynchronizedNotificationHandler implements NotificationHandler {

	private TreasuryYealdMonthly10YearSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("IndicatorsSynchronizationSupervisorApplicationService")
	private IndicatorsSynchronizationSupervisorService service;
	
	public TreasuryYealdMonthly10YearSynchronizedNotificationHandler(
			@Qualifier("IndicatorsNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, TreasuryYealdMonthly10YearSynchronizedNotification.class);
			
			if(Subject.TREASURY_YEALD_MONTHLY_10_YEAR_SYNCED.equals(notification.getSubject())) {
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

	private Command<IndicatorsSynchronizationSupervisorService>  buildCommand(TreasuryYealdMonthly10YearSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentIndicatorsSynchronizationStateCommand(
				service,
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}


}
