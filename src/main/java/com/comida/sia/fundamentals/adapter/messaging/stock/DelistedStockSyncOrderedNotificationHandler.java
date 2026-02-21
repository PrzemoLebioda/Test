package com.comida.sia.fundamentals.adapter.messaging.stock;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.stock.StockService;
import com.comida.sia.fundamentals.port.application.stock.SynchronizeDelistedStockCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.stock.DelistedStockSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.stock.DelistedStockSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("DelistedStockSyncOrderedNotificationHandler")
public class DelistedStockSyncOrderedNotificationHandler implements NotificationHandler {

	private DelistedStockSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("StockApplicationService")
	private StockService service;
	
	public DelistedStockSyncOrderedNotificationHandler(
			@Qualifier("StockNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, DelistedStockSynchronizationOrderedNotification.class);
			
			if(Subject.DELISTED_STOCKS_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<StockService> buildCommand(DelistedStockSynchronizationOrderedDomainEvent domainEvent){
		return new SynchronizeDelistedStockCommand(service, domainEvent.getSyncState());
	}

}
