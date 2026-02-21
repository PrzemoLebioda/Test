package com.comida.sia.fundamentals.adapter.messaging.stock;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.stock.StockService;
import com.comida.sia.fundamentals.port.application.stock.SynchronizeListedStockCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.stock.ListedStockSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.stock.ListedStockSynchronizationOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("ListedStockSyncOrderedNotificationHandler")
public class ListedStockSyncOrderedNotificationHandler implements NotificationHandler {

	private ListedStockSynchronizationOrderedNotification notification;
	
	@Autowired
	@Qualifier("StockApplicationService")
	private StockService service;
	
	public ListedStockSyncOrderedNotificationHandler(
			@Qualifier("StockNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ListedStockSynchronizationOrderedNotification.class);
			
			if(Subject.LISTED_STOCKS_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<StockService> buildCommand(ListedStockSynchronizationOrderedDomainEvent domainEvent){
		return new SynchronizeListedStockCommand(service, domainEvent.getSyncState());
	}

}
