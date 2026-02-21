package com.comida.sia.fundamentals.adapter.messaging.balancesheet;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.port.application.balancesheet.BalanceSheetsRegisterService;
import com.comida.sia.fundamentals.port.application.balancesheet.SynchronizeBalanceSheetAnnualReportCommand;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.port.messaging.company.BalanceSheetAnnualReportSyncOrderedNotification;
import com.opencsv.exceptions.CsvException;

@Component("BalanceSheetAnnualReportSyncOrderedNotificationHandler")
public class BalanceSheetAnnualReportSyncOrderedNotificationHandler implements NotificationHandler{
	
	private BalanceSheetAnnualReportSyncOrderedNotification notification;
	
	@Autowired
	@Qualifier("BalanceSheetsRegisterApplicationService")
	private BalanceSheetsRegisterService service;
	
	public BalanceSheetAnnualReportSyncOrderedNotificationHandler(
			@Qualifier("BalanceSheetReportSyncOrderedNotificationSubscriber") NotificationSubscriber subscriber) {
		
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, BalanceSheetAnnualReportSyncOrderedNotification.class);
			
			if(Subject.BALANCE_SHEET_ANNUAL_REPORT_SYNC_ORDERED.equals(notification.getSubject())) {
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
	
	private Command<BalanceSheetsRegisterService> buildCommand(BalanceSheetAnnualReportSyncOrderedDomainEvent domainEvent) {	
		return new SynchronizeBalanceSheetAnnualReportCommand(
				service,
				domainEvent.getTag(),
				domainEvent.getSyncState());
	}
}
