package com.comida.sia.sync.supervision.adapter.messaging.exchangequote;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.stock.StockDelistedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.stock.StockDelistedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;
import com.comida.sia.sync.supervision.port.application.exchangequote.DeactivateExchangeQuotesSynchronizationCommand;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.application.exchangequote.InterdayExchangeQuotesSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("StockDelistedForQuotationsNotificationHandler")
public class StockDelistedNotificationHandler implements NotificationHandler{

	private StockDelistedNotification notification;
	
	@Autowired
	@Qualifier("ExchangeQuotesSynchronizationSupervisorApplicationService")
	private ExchangeQuotesSynchronizationSupervisorService adjustedExchangeQuotesService;
	
	@Autowired
	@Qualifier("InterdayExchangeQuotesSynchronizationSupervisorApplicationService")
	private InterdayExchangeQuotesSynchronizationSupervisorService interdayExchangeQuotesService;
	
	public StockDelistedNotificationHandler(
			@Qualifier("ExchangeQuoteNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, StockDelistedNotification.class);
			
			if(notification.getSubject().equals(Subject.STOCK_DELISTED)) {
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
		buildDeactivateAdjustedQuotationsSyncCommand(notification.getPayload()).execute();
		buildDeactivateInterdayQuotationsSyncCommand(notification.getPayload()).execute();
	}

	private Command<ExchangeQuotesSynchronizationSupervisorService> buildDeactivateAdjustedQuotationsSyncCommand(StockDelistedDomainEvent domainEvent) {
		return new DeactivateExchangeQuotesSynchronizationCommand(
				adjustedExchangeQuotesService, 
				domainEvent.getTickerSymbol(), 
				AssetType.get(domainEvent.getAssetType().getName()));
	}
	
	private Command<ExchangeQuotesSynchronizationSupervisorService> buildDeactivateInterdayQuotationsSyncCommand(StockDelistedDomainEvent domainEvent) {
		return new DeactivateExchangeQuotesSynchronizationCommand(
				interdayExchangeQuotesService, 
				domainEvent.getTickerSymbol(), 
				AssetType.get(domainEvent.getAssetType().getName()));
	}
}
