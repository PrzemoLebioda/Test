package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.stock.StockListedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.stock.StockListedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.application.exchangequote.InterdayExchangeQuotesSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.application.exchangequote.RegisterExchangeQuotesSynchronizationSupervisorCommand;
import com.opencsv.exceptions.CsvException;

@Component("StockListedForQuotationsNotificationHandler")
public class StockListedNotificationHandler implements NotificationHandler{
	
	private StockListedNotification notification;
	
	@Autowired
	@Qualifier("ExchangeQuotesSynchronizationSupervisorApplicationService")
	private ExchangeQuotesSynchronizationSupervisorService adjustedExchangeQuotesService;
	
	@Autowired
	@Qualifier("InterdayExchangeQuotesSynchronizationSupervisorApplicationService")
	private InterdayExchangeQuotesSynchronizationSupervisorService interdayExchangeQuotesService;
	
	public StockListedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, StockListedNotification.class);
			
			if(notification.getSubject().equals(Subject.STOCK_LISTED)) {
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
		buildRegisterAdjustedQuotationsSyncCommand(notification.getPayload()).execute();
		buildRegisterInterdayQuotationsSyncCommand(notification.getPayload()).execute();
	}

	private Command<ExchangeQuotesSynchronizationSupervisorService> buildRegisterAdjustedQuotationsSyncCommand(StockListedDomainEvent domainEvent) {
		return new RegisterExchangeQuotesSynchronizationSupervisorCommand(
				adjustedExchangeQuotesService, 
				domainEvent.getTickerSymbol(), 
				AssetType.get(domainEvent.getAssetType().getName()));
	}
	
	private Command<ExchangeQuotesSynchronizationSupervisorService> buildRegisterInterdayQuotationsSyncCommand(StockListedDomainEvent domainEvent){
		return new RegisterExchangeQuotesSynchronizationSupervisorCommand(
				interdayExchangeQuotesService, 
				domainEvent.getTickerSymbol(), 
				AssetType.get(domainEvent.getAssetType().getName()));
	}
}
