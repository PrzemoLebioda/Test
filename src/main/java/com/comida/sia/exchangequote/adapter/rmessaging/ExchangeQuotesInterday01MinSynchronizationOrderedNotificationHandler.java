package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.port.application.InterdayExchangeQuoteService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.exchangequote.ExchangeQuotesInterday01MinSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.exchangequote.port.application.SynchronizeInterday01MinQuotationsCommand;

@Component("ExchangeQuotesInterday01MinSynchronizationOrderedNotificationHandler")
public class ExchangeQuotesInterday01MinSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private ExchangeQuotesInterday01MinSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("InterdayExchangeQuoteApplicationService")
	private InterdayExchangeQuoteService service;
	
	public ExchangeQuotesInterday01MinSynchronizationOrderedNotificationHandler(
			@Qualifier("InterdayExchangeQuoteNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuotesInterday01MinSynchronizationOrderedNotification.class);
			
			if(Subject.EXCHANGE_QUOTE_INTERDAY_01_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<InterdayExchangeQuoteService> buildCommand(ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeInterday01MinQuotationsCommand(
					service,
					domainEvent.getTag(), 
					domainEvent.getSyncState()
				);
	}
	
}
