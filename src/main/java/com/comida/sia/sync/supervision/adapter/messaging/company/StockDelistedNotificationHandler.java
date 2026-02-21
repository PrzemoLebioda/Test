package com.comida.sia.sync.supervision.adapter.messaging.company;

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
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.application.company.DeactivateCompanySynchronizationCommand;
import com.opencsv.exceptions.CsvException;

@Component("StockDelistedNotificationHandler")
public class StockDelistedNotificationHandler implements NotificationHandler{
	
	private StockDelistedNotification notification;
	
	@Autowired
	@Qualifier("CompanySynchronizationSupervisorApplicationService")
	private CompanySynchronizationSupervisorService companySynchronizationSupervisorService;
	
	public StockDelistedNotificationHandler(
			@Qualifier("CompanyDataSynchronizedNotificationSubscriber")NotificationSubscriber subscriber) {
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
		buildCommand(notification.getPayload()).execute();
	}
	
	private Command<CompanySynchronizationSupervisorService> buildCommand(StockDelistedDomainEvent domainEvent){
		return new DeactivateCompanySynchronizationCommand(
				companySynchronizationSupervisorService, 
				domainEvent.getTickerSymbol(),
				domainEvent.getDelistedDate(),
				AssetType.get(domainEvent.getAssetType().getName()));
	}
}
