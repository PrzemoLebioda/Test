package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent;
import com.comida.sia.exchangequote.port.messaging.ExchangeQuoteWeeklyAdjustedSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.exchangequote.CalculateCurrentExchangeQuotesSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuoteWeeklyAdjustedSynchronizedNotificationHandler")
public class ExchangeQuoteWeeklyAdjustedSynchronizedNotificationHandler implements NotificationHandler{

	private ExchangeQuoteWeeklyAdjustedSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("ExchangeQuotesSynchronizationSupervisorApplicationService")
	private ExchangeQuotesSynchronizationSupervisorService service;

	public ExchangeQuoteWeeklyAdjustedSynchronizedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuoteWeeklyAdjustedSynchronizedNotification.class);
			
			if(notification.getSubject().equals(Subject.EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNCED)) {
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

	private Command<ExchangeQuotesSynchronizationSupervisorService> buildCommand(ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentExchangeQuotesSynchronizationStateCommand(
				service,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}
}
