package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday15MinSynchronizedDomainEvent;
import com.comida.sia.exchangequote.port.messaging.ExchangeQuoteInterday15MinSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.exchangequote.CalculateCurrentExchangeQuotesSynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("ExchangeQuoteInterday15MinSynchronizedNotificationHandler")
public class ExchangeQuoteInterday15MinSynchronizedNotificationHandler implements NotificationHandler{
	
	private ExchangeQuoteInterday15MinSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("InterdayExchangeQuotesSynchronizationSupervisorApplicationService")
	private ExchangeQuotesSynchronizationSupervisorService service;
	
	public ExchangeQuoteInterday15MinSynchronizedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ExchangeQuoteInterday15MinSynchronizedNotification.class);
			
			if(notification.getSubject().equals(Subject.EXCHANGE_QUOTE_INTERDAY_15_SYNCED)) {
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

	private Command<ExchangeQuotesSynchronizationSupervisorService> buildCommand(ExchangeQuoteInterday15MinSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentExchangeQuotesSynchronizationStateCommand(
				service,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}
}
