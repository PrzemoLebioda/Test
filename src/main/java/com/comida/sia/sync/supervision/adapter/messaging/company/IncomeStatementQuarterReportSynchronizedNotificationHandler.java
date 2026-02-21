package com.comida.sia.sync.supervision.adapter.messaging.company;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.income.IncomeStatementQuarterReportSynchronizedDomainEvent;
import com.comida.sia.fundamentals.port.messaging.income.IncomeStatementQuarterReportSynchronizedNotification;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.NotificationHandler;
import com.comida.sia.sharedkernel.messaging.NotificationSerializer;
import com.comida.sia.sharedkernel.messaging.NotificationSubscriber;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.port.application.company.CalculateCurrentCompanySynchronizationStateCommand;
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.opencsv.exceptions.CsvException;

@Component("IncomeStatementQuarterReportSynchronizedNotificationHandler")
public class IncomeStatementQuarterReportSynchronizedNotificationHandler implements NotificationHandler {

	private IncomeStatementQuarterReportSynchronizedNotification notification;

	@Autowired
	@Qualifier("CompanySynchronizationSupervisorApplicationService")
	private CompanySynchronizationSupervisorService companySynchronizationSupervisorService;

	public IncomeStatementQuarterReportSynchronizedNotificationHandler(
			@Qualifier("CompanyDataSynchronizedNotificationSubscriber")NotificationSubscriber subscriber) {
		subscriber.register(this);
	}
	
	@Override
	public boolean canHandle(String message) {
		try {
			notification = NotificationSerializer
					.instance()
					.deserialize(message, IncomeStatementQuarterReportSynchronizedNotification.class);
			
			if(Subject.INCOME_QUARTER_REPORT_SYNCED.equals(notification.getSubject())) {
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

	private Command<CompanySynchronizationSupervisorService> buildCommand(IncomeStatementQuarterReportSynchronizedDomainEvent domainEvent) {
		return new CalculateCurrentCompanySynchronizationStateCommand(
				companySynchronizationSupervisorService,
				domainEvent.getTickerSymbol(),
				domainEvent.getSubject(),
				domainEvent.getSummary());
	}

}
