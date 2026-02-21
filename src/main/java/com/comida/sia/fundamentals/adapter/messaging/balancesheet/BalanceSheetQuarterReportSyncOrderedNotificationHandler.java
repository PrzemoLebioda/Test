package com.comida.sia.fundamentals.adapter.messaging.balancesheet;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.balancesheet.BalanceSheetsRegisterService;
import com.comida.sia.fundamentals.port.application.balancesheet.SynchronizeBalanceSheetQuarterReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.BalanceSheetQuarterReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("BalanceSheetQuarterReportSyncOrderedNotificationHandler")
public class BalanceSheetQuarterReportSyncOrderedNotificationHandler implements NotificationHandler{

	private BalanceSheetQuarterReportSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("BalanceSheetsRegisterApplicationService")
	private BalanceSheetsRegisterService service;
	
	public BalanceSheetQuarterReportSyncOrderedNotificationHandler(
			@Qualifier("BalanceSheetReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, BalanceSheetQuarterReportSyncOrderedNotification.class);
			
			if(Subject.BALANCE_SHEET_QUARTER_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<BalanceSheetsRegisterService> buildCommand(BalanceSheetQuarterReportSyncOrderedDomainEvent domainEvent) {	
		return new SynchronizeBalanceSheetQuarterReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}

}
