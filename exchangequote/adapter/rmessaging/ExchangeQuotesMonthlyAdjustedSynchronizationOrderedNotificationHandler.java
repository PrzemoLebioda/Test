package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.port.application.AdjustedExchangeQuoteService;
import com.comida.sia.exchangequote.port.application.SynchronizeMonthlyAdjustedQuotationsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.exchangequote.ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotificationHandler")
public class ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotification notification;

	@Autowired
	@Qualifier("AdjustedExchangeQuoteApplicationService")
	private AdjustedExchangeQuoteService service;
	
	public ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotificationHandler(
			@Qualifier("AdjustedExchangeQuoteNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotification.class);
			
			if(Subject.EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<AdjustedExchangeQuoteService> buildCommand(ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeMonthlyAdjustedQuotationsCommand(
				service,
				domainEvent.getTag(), 
				domainEvent.getSyncState()
			);
	}
}
