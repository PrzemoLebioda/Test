package com.comida.sia.sync.supervision.adapter.messaging.company;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReportSynchronizedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.earnings.estimates.EarningEstimatesReportSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.company.CalculateCurrentCompanySynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("EarningEstimatesReportSynchronizedNotificationHandler")
public class EarningEstimatesReportSynchronizedNotificationHandler implements NotificationHandler {

	private EarningEstimatesReportSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("CompanySynchronizationSupervisorApplicationService")
	private CompanySynchronizationSupervisorService companySynchronizationSupervisorService;
	
	public EarningEstimatesReportSynchronizedNotificationHandler(
			@Qualifier("CompanyDataSynchronizedNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, EarningEstimatesReportSynchronizedNotification.class);
			
			if(Subject.EARNINGS_ESTIMATES_SYNCED.equals(notification.getSubject())) {
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

	private Command<CompanySynchronizationSupervisorService> buildCommand(EarningEstimatesReportSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentCompanySynchronizationStateCommand(
				companySynchronizationSupervisorService,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}

}
