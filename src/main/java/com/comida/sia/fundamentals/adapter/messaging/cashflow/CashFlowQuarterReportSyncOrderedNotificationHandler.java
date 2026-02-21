package com.comida.sia.fundamentals.adapter.messaging.cashflow;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.cashflow.CashFlowsRegisterService;
import com.comida.sia.fundamentals.port.application.cashflow.SynchronizeCashFlowQuarterReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.CashflowQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.CashflowQuarterReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;



@Component("CashFlowQuarterReportSyncOrderedNotificationHandler")
public class CashFlowQuarterReportSyncOrderedNotificationHandler implements NotificationHandler {

private CashflowQuarterReportSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("CashFlowsRegisterApplicationService")
	private CashFlowsRegisterService service;
	
	public CashFlowQuarterReportSyncOrderedNotificationHandler(
			@Qualifier("CashFlowReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, CashflowQuarterReportSyncOrderedNotification.class);
			
			if(Subject.CASH_FLOW_QUARTER_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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

	private Command<CashFlowsRegisterService> buildCommand(CashflowQuarterReportSyncOrderedDomainEvent domainEvent){
		return new SynchronizeCashFlowQuarterReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
	
}
