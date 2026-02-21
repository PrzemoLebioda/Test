package com.comida.sia.exchangequote.adapter.rmessaging;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.port.application.AdjustedExchangeQuoteService;
import com.comida.sia.exchangequote.port.application.SynchronizeDailyAdjustedQuotationsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.exchangequote.ExchangeQuotesDailyAdjustedSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuotesDailyAdjustedSynchronizationOrderedNotificationHandler")
public class ExchangeQuotesDailyAdjustedSynchronizationOrderedNotificationHandler implements NotificationHandler {

	private ExchangeQuotesDailyAdjustedSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("AdjustedExchangeQuoteApplicationService")
	private AdjustedExchangeQuoteService service;
	
	public ExchangeQuotesDailyAdjustedSynchronizationOrderedNotificationHandler(
			@Qualifier("AdjustedExchangeQuoteNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuotesDailyAdjustedSynchronizationOrderedNotification.class);
			
			if(Subject.EXCHANGE_QUOTE_DAILY_ADJUSTED_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<AdjustedExchangeQuoteService> buildCommand(ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeDailyAdjustedQuotationsCommand(
					service,
					domainEvent.getTag(), 
					domainEvent.getSyncState()
				);
	}

}
