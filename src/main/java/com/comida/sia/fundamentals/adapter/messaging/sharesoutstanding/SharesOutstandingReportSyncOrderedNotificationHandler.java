package com.comida.sia.fundamentals.adapter.messaging.sharesoutstanding;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.sharesoutstanding.SharesOutstandingService;
import com.comida.sia.fundamentals.port.application.sharesoutstanding.SynchronizeSharesOutstandingReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.SharesOutstandingReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.SharesOutstandingReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("SharesOutstandingReportSyncOrderedNotificationHandler")
public class SharesOutstandingReportSyncOrderedNotificationHandler implements NotificationHandler {

	private SharesOutstandingReportSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("SharesOutstandingApplicationService")
	private SharesOutstandingService service;
	
	public SharesOutstandingReportSyncOrderedNotificationHandler(
			@Qualifier("SharesOutstandingReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, SharesOutstandingReportSyncOrderedNotification.class);
			
			if(Subject.SHARES_OUTSTANDING_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<SharesOutstandingService> buildCommand(SharesOutstandingReportSyncOrderedDomainEvent domainEvent){
		return new SynchronizeSharesOutstandingReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
}
