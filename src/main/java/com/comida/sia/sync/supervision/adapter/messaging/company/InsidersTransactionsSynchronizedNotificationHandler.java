package com.comida.sia.sync.supervision.adapter.messaging.company;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransactionsSynchronizedDomainEvent;
import com.comida.sia.intelligence.insidertransactions.port.messaging.InsidersTransactionsSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.company.CalculateCurrentCompanySynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("InsidersTransactionsSynchronizedNotificationHandler")
public class InsidersTransactionsSynchronizedNotificationHandler implements NotificationHandler {

	private InsidersTransactionsSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("CompanySynchronizationSupervisorApplicationService")
	private CompanySynchronizationSupervisorService companySynchronizationSupervisorService;
	
	public InsidersTransactionsSynchronizedNotificationHandler(
			@Qualifier("CompanyDataSynchronizedNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, InsidersTransactionsSynchronizedNotification.class);
			
			if(Subject.INSIDER_TRANSACTIONS_SYNCED.equals(notification.getSubject())) {
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

	private Command<CompanySynchronizationSupervisorService> buildCommand(InsiderTransactionsSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentCompanySynchronizationStateCommand(
				companySynchronizationSupervisorService,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}

}
