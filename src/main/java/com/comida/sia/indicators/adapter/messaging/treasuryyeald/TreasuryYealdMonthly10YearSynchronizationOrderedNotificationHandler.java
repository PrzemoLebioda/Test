package com.comida.sia.indicators.adapter.messaging.treasuryyeald;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.treasuryyeald.SynchronizeTreasuryYealdMonthly10YearCommand;
import com.comida.sia.indicators.port.application.treasuryyeald.TreasuryYealdService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.TreasuryYealdMonthly10YearSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("TreasuryYealdMonthly10YearSynchronizationOrderedNotificationHandler")
public class TreasuryYealdMonthly10YearSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private TreasuryYealdMonthly10YearSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("TreasuryYealdApplicationService")
	private TreasuryYealdService service;
	
	public TreasuryYealdMonthly10YearSynchronizationOrderedNotificationHandler(
			@Qualifier("TreasuryYealdSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, TreasuryYealdMonthly10YearSynchronizationOrderedNotification.class);
			
			if(Subject.TREASURY_YEALD_MONTHLY_10_YEAR_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<TreasuryYealdService> buildCommand(TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeTreasuryYealdMonthly10YearCommand(
				service,
				domainEvent.getSyncState());
	}

}
