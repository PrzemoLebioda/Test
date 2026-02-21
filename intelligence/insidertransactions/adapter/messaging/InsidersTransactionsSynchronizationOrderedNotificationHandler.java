package com.comida.sia.intelligence.insidertransactions.adapter.messaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.intelligence.insidertransactions.port.application.InsiderTransactionService;
import com.comida.sia.intelligence.insidertransactions.port.application.RegisterInsiderTransactionsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.InsidersTransactionsSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.InsidersTransactionsSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("InsidersTransactionsSynchronizationOrderedNotificationHandler")
public class InsidersTransactionsSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private  InsidersTransactionsSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("InsiderTransactionApplicationService")
	private InsiderTransactionService service;
	
	public InsidersTransactionsSynchronizationOrderedNotificationHandler(
			@Qualifier("InsidersTransactionsSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, InsidersTransactionsSynchronizationOrderedNotification.class);
			
			if(Subject.INSIDER_TRANSACTIONS_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<InsiderTransactionService> buildCommand(InsidersTransactionsSynchronizationOrderedDomainEvent domainEvent) {
		return new RegisterInsiderTransactionsCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
