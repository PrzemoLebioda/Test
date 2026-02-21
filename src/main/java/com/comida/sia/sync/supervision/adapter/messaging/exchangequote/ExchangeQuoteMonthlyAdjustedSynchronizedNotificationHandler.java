package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent;
import com.comida.sia.exchangequote.port.messaging.ExchangeQuoteMonthlyAdjustedSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.exchangequote.CalculateCurrentExchangeQuotesSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuoteMonthlyAdjustedSynchronizedNotificationHandler")
public class ExchangeQuoteMonthlyAdjustedSynchronizedNotificationHandler implements NotificationHandler {

	private ExchangeQuoteMonthlyAdjustedSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("ExchangeQuotesSynchronizationSupervisorApplicationService")
	private ExchangeQuotesSynchronizationSupervisorService service;

	public ExchangeQuoteMonthlyAdjustedSynchronizedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuoteMonthlyAdjustedSynchronizedNotification.class);
			
			if(notification.getSubject().equals(Subject.EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNCED)) {
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

	private Command<ExchangeQuotesSynchronizationSupervisorService> buildCommand(ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentExchangeQuotesSynchronizationStateCommand(
				service,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}
}
