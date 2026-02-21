package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.port.application.AdjustedExchangeQuoteService;
import com.comida.sia.exchangequote.port.application.SynchronizeWeeklyAdjustedQuotationsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.exchangequote.ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotificationHandler")
public class ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotificationHandler implements NotificationHandler {
	
	private ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("AdjustedExchangeQuoteApplicationService")
	private AdjustedExchangeQuoteService service;
	
	public ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotificationHandler(
			@Qualifier("AdjustedExchangeQuoteNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotification.class);
			
			if(Subject.EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<AdjustedExchangeQuoteService> buildCommand(ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeWeeklyAdjustedQuotationsCommand(
				service,
				domainEvent.getTag(), 
				domainEvent.getSyncState()
			);
	}
}
