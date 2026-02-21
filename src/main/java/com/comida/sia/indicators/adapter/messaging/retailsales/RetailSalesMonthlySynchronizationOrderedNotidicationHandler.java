package com.comida.sia.indicators.adapter.messaging.retailsales;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.port.application.retailsales.RetailSalesService;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.indicators.events.RetailSalesMonthlySynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.indicators.RetailSalesMonthlySynchronizationOrderedNotidication;
import com.opencsv.exceptions.CsvException;
import com.comida.sia.indicators.port.application.retailsales.SynchronizeMonthlyRetailSalesCommand;

@Component("RetailSalesMonthlySynchronizationOrderedNotidicationHandler")
public class RetailSalesMonthlySynchronizationOrderedNotidicationHandler implements NotificationHandler {

	private RetailSalesMonthlySynchronizationOrderedNotidication notification;

	@Autowired
	@Qualifier("RetailSalesApplicationService")
	private RetailSalesService service;
	
	public RetailSalesMonthlySynchronizationOrderedNotidicationHandler(
			@Qualifier("RetailSalesNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, RetailSalesMonthlySynchronizationOrderedNotidication.class);
			
			if(Subject.RETAIL_SALES_MONTHLY_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<RetailSalesService> buildCommand(RetailSalesMonthlySynchronizationOrderedDomainEvent domainEvent) {
		return new SynchronizeMonthlyRetailSalesCommand(
				service,
				domainEvent.getSyncState());
	}
}
