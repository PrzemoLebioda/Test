package com.comida.sia.fundamentals.adapter.messaging.company;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.application.CompanyApplicationService;
import com.comida.sia.fundamentals.port.application.CompanyService;
import com.comida.sia.fundamentals.port.application.SynchronizeCompnyKeyMetricsCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.CompanyKeyMetricsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.CompanyKeyMetricsSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("CompanyKeyMetricsSyncOrderedNotificationHandler")
public class CompanyKeyMetricsSyncOrderedNotificationHandler implements NotificationHandler {

	private CompanyKeyMetricsSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("CompanyApplicationService")
	private CompanyApplicationService service;
	
	public CompanyKeyMetricsSyncOrderedNotificationHandler(
			@Qualifier("CompanyKeyMetricsSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, CompanyKeyMetricsSyncOrderedNotification.class);
			
			if(Subject.COMPANY_KEY_METRICS_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<CompanyService> buildCommand(CompanyKeyMetricsSyncOrderedDomainEvent domainEvent){
		return new SynchronizeCompnyKeyMetricsCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
