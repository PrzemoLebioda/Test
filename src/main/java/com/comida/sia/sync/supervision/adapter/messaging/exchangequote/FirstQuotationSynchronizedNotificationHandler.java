package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.FirstQuotationSynchronizedDomainEvent;
import com.comida.sia.exchangequote.port.messaging.FirstQuotationSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.exchangequote.EstablishStartsynchronizationTimeCommand;
import com.comida.sia.sync.supervision.port.application.exchangequote.InterdayExchangeQuotesSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("FirstQuotationSynchronizedNotificationHandler")
public class FirstQuotationSynchronizedNotificationHandler implements NotificationHandler{

	private FirstQuotationSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("InterdayExchangeQuotesSynchronizationSupervisorApplicationService")
	private InterdayExchangeQuotesSynchronizationSupervisorService interdayExchangeQuotesService;
	
	public FirstQuotationSynchronizedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, FirstQuotationSynchronizedNotification.class);
			
			if(notification.getSubject().equals(Subject.FIRST_QUITATION_SYNCED)) {
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

	private Command<InterdayExchangeQuotesSynchronizationSupervisorService> buildCommand(FirstQuotationSynchronizedDomainEvent domainEvent) {
		return new EstablishStartsynchronizationTimeCommand(
				interdayExchangeQuotesService, 
				domainEvent.getTickerSymbol(),
				domainEvent.getFirstQuotationTime());
	}

}
