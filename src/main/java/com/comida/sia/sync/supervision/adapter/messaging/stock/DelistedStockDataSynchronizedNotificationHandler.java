package com.comida.sia.sync.supervision.adapter.messaging.stock;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.stock.DelistedStockSynchronizedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.stock.DelistedStocksSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.stock.CalculateCurrentDelistedStockSyncStateCommand;
import com.comida.sia.sync.supervision.port.application.stock.StockSynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("DelistedStockDataSynchronizedNotificationHandler")
public class DelistedStockDataSynchronizedNotificationHandler implements NotificationHandler {
	
	private DelistedStocksSynchronizedNotification notification;
	
	@Autowired
	@Qualifier("StockSynchronizationSupervisorApplicationService")
	private StockSynchronizationSupervisorService stockSynchronizationSupervisorService;
	
	public DelistedStockDataSynchronizedNotificationHandler(
			@Qualifier("StockSyncNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}

	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, DelistedStocksSynchronizedNotification.class);
			
			if(Subject.DELISTED_STOCK_SYNCED.equals(notification.getSubject())) {
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
	
	private Command<StockSynchronizationSupervisorService> buildCommand(DelistedStockSynchronizedDomainEvent domainEvent){
		return new CalculateCurrentDelistedStockSyncStateCommand(
				stockSynchronizationSupervisorService,
				domainEvent.getSummary());
	}
}
