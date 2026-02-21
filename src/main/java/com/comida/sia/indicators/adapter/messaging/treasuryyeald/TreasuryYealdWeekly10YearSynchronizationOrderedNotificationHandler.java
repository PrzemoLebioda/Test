package com.comida.sia.indicators.adapter.messaging.treasuryyeald;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.treasuryyeald.SynchronizeTreasuryYealdWeekly10YearCommand;
import com.comida.sia.indicators.port.application.treasuryyeald.TreasuryYealdService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.TreasuryYealdWeekly10YearSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("TreasuryYealdWeekly10YearSynchronizationOrderedNotificationHandler")
public class TreasuryYealdWeekly10YearSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private TreasuryYealdWeekly10YearSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("TreasuryYealdApplicationService")
	private TreasuryYealdService service;
	
	public TreasuryYealdWeekly10YearSynchronizationOrderedNotificationHandler(
			@Qualifier("TreasuryYealdSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, TreasuryYealdWeekly10YearSynchronizationOrderedNotification.class);
			
			if(Subject.TREASURY_YEALD_WEEKLY_10_YEAR_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<TreasuryYealdService> buildCommand(TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeTreasuryYealdWeekly10YearCommand(
				service,
				domainEvent.getSyncState());
	}

}
