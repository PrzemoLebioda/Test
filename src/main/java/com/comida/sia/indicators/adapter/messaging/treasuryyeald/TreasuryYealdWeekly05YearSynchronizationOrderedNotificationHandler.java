package com.comida.sia.indicators.adapter.messaging.treasuryyeald;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.treasuryyeald.SynchronizeTreasuryYealdWeekly05YearCommand;
import com.comida.sia.indicators.port.application.treasuryyeald.TreasuryYealdService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.TreasuryYealdWeekly05YearSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("TreasuryYealdWeekly05YearSynchronizationOrderedNotificationHandler")
public class TreasuryYealdWeekly05YearSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private TreasuryYealdWeekly05YearSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("TreasuryYealdApplicationService")
	private TreasuryYealdService service;
	
	public TreasuryYealdWeekly05YearSynchronizationOrderedNotificationHandler(
			@Qualifier("TreasuryYealdSyncNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, TreasuryYealdWeekly05YearSynchronizationOrderedNotification.class);
			
			if(Subject.TREASURY_YEALD_WEEKLY_05_YEAR_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<TreasuryYealdService> buildCommand(TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeTreasuryYealdWeekly05YearCommand(
				service,
				domainEvent.getSyncState());
	}

}
