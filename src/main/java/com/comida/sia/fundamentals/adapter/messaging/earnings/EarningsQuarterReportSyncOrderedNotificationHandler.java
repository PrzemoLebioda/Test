package com.comida.sia.fundamentals.adapter.messaging.earnings;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.earnings.EarningsRegisterService;
import com.comida.sia.fundamentals.port.application.earnings.SynchronizeEarningsQuarterReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.EarningsQuarterReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("EarningsQuarterReportSyncOrderedNotificationHandler")
public class EarningsQuarterReportSyncOrderedNotificationHandler implements NotificationHandler {

	private EarningsQuarterReportSyncOrderedNotification notification;
	
	public EarningsQuarterReportSyncOrderedNotificationHandler(
			@Qualifier("EarningsReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		
		subscriber.register(this);
	}
	
	@Autowired
	@Qualifier("EarningsRegisterApplicationService")
	private EarningsRegisterService service;
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningsQuarterReportSyncOrderedNotification.class);
			
			if(Subject.EARNINGS_QUARTER_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<EarningsRegisterService> buildCommand(EarningsQuarterReportSyncOrderedDomainEvent domainEvent){
		return new SynchronizeEarningsQuarterReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
