package com.comida.sia.fundamentals.adapter.messaging.income;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.income.IncomeStatementsRegisterService;
import com.comida.sia.fundamentals.port.application.income.SynchronizeIncomeStatementAnnualReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.IncomeAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.IncomeAnnualReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("IncomeStatementAnnualReportSyncOrderedNotificationHandler")
public class IncomeStatementAnnualReportSyncOrderedNotificationHandler implements NotificationHandler {

	private IncomeAnnualReportSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("IncomeStatementsRegisterApplicationService")
	private IncomeStatementsRegisterService service;
	
	public IncomeStatementAnnualReportSyncOrderedNotificationHandler(
			@Qualifier("IncomeStatementReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IncomeAnnualReportSyncOrderedNotification.class);
			
			if(Subject.INCOME_ANNUAL_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<IncomeStatementsRegisterService> buildCommand(IncomeAnnualReportSyncOrderedDomainEvent domainEvent){
		return new SynchronizeIncomeStatementAnnualReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
