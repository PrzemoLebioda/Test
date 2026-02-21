package com.comida.sia.fundamentals.adapter.messaging.earnings.estimation;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.earnings.estimates.EarningEstimatesReportService;
import com.comida.sia.fundamentals.port.application.earnings.estimates.SynchronizeEarningEstimatesReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsEstimatesSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.EarningsEstimatesSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("EarningEstimatesReportSyncOrderedNotificationHandler")
public class EarningEstimatesReportSyncOrderedNotificationHandler implements NotificationHandler {

	private EarningsEstimatesSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("EarningEstimatesReportApplicationService")
	private EarningEstimatesReportService service;
	
	public EarningEstimatesReportSyncOrderedNotificationHandler(
			@Qualifier("EarningEstimatesReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningsEstimatesSyncOrderedNotification.class);
			
			if(Subject.EARNINGS_ESTIMATES_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<EarningEstimatesReportService> buildCommand(EarningsEstimatesSyncOrderedDomainEvent domainEvent){
		return new SynchronizeEarningEstimatesReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
}
