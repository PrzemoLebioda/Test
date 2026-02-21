package com.comida.sia.sync.supervision.adapter.messaging.stock;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.stock.ListedStockSynchronizedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.stock.ListedStocksSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.stock.CalculateCurrentListedStockSyncStateCommand;
import com.comida.sia.sync.supervision.port.application.stock.StockSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("ListedStockDataSynchronizedNotificationHandler")
public class ListedStockDataSynchronizedNotificationHandler implements NotificationHandler {

	private ListedStocksSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("StockSynchronizationSupervisorApplicationService")
	private StockSynchronizationSupervisorService stockSynchronizationSupervisorService;
	
	public ListedStockDataSynchronizedNotificationHandler(
			@Qualifier("StockSyncNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, ListedStocksSynchronizedNotification.class);
			
			if(Subject.LISTED_STOCK_SYNCED.equals(notification.getSubject())) {
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
	
	private Command<StockSynchronizationSupervisorService> buildCommand(ListedStockSynchronizedDomainEvent domainEvent){
		return new CalculateCurrentListedStockSyncStateCommand(
				stockSynchronizationSupervisorService,
				domainEvent.getSummary());
	}

}
