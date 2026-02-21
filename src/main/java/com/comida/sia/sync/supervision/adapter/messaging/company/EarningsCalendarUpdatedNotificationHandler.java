package com.comida.sia.sync.supervision.adapter.messaging.company;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.EarningsCalendarUpdatedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.corpoevents.earnings.EarningsCalendarUpdatedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.application.company.ScheduleSynchronizationCommand;
import com.opencsv.exceptions.CsvException;

@Component("EarningsCalendarUpdatedNotificationHandler")
public class EarningsCalendarUpdatedNotificationHandler implements NotificationHandler{

	private EarningsCalendarUpdatedNotification notification;

	@Autowired
	@Qualifier("CompanySynchronizationSupervisorApplicationService")
	private CompanySynchronizationSupervisorService companySynchronizationSupervisorService;
	
	public EarningsCalendarUpdatedNotificationHandler(
			@Qualifier("CompanyDataSynchronizedNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningsCalendarUpdatedNotification.class);
			
			if(Subject.CALENDAR_EARNINGS_UPDATED.equals(notification.getSubject())) {
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

	private Command<CompanySynchronizationSupervisorService> buildCommand(EarningsCalendarUpdatedDomainEvent domainEvent) {
		return new ScheduleSynchronizationCommand(
				companySynchronizationSupervisorService,
				domainEvent.getTickerSymbol(),
				domainEvent.getNextReportExpectedDate());
	}
}
