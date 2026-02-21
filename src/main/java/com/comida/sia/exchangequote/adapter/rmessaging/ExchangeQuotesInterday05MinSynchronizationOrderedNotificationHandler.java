package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.port.application.InterdayExchangeQuoteService;
import com.comida.sia.exchangequote.port.application.SynchronizeInterday05MinQuotationsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.exchangequote.ExchangeQuotesInterday05MinSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuotesInterday05MinSynchronizationOrderedNotificationHandler")
public class ExchangeQuotesInterday05MinSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private ExchangeQuotesInterday05MinSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("InterdayExchangeQuoteApplicationService")
	private InterdayExchangeQuoteService service;
	
	public ExchangeQuotesInterday05MinSynchronizationOrderedNotificationHandler(
			@Qualifier("InterdayExchangeQuoteNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuotesInterday05MinSynchronizationOrderedNotification.class);
			
			if(Subject.EXCHANGE_QUOTE_INTERDAY_05_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<InterdayExchangeQuoteService> buildCommand(ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeInterday05MinQuotationsCommand(
				service,
				domainEvent.getTag(), 
				domainEvent.getSyncState()
			);
	}

}
