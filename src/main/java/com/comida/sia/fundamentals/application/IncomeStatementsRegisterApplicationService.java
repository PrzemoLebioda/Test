package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.income.IncomeStatementReportsSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.income.IncomeStatementReport;
import com.comida.sia.fundamentals.domain.model.income.IncomeStatementReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementDataAcquirer;
import com.comida.sia.fundamentals.port.application.income.IncomeStatementsRegisterService;
import com.comida.sia.fundamentals.port.repository.IncomeStatementRepository;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("IncomeStatementsRegisterApplicationService")
public class IncomeStatementsRegisterApplicationService extends AssertionConcern implements IncomeStatementsRegisterService, Notifier {
	
	@Autowired
	private IncomeStatementRepository repository;
	
	@Autowired
	private IncomeStatementDataAcquirer acquirer;
	
	@Autowired
	IncomeStatementReportsSynchronizedNotificationPublisher publisher;

	@Override
	@Transactional
	public void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			IncomeStatementReportSynchronizationAdminAssembler assembler = new IncomeStatementReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyIncomeStatementAnnualReportSyncAdmin(syncState, this)
							.synchronize(
								acquirer.gatherIncomeStatementDataFor(tickerSymbol, syncState).getAnnualReports(), 
								syncState
							)
					);
			
		} catch (EmptyListException e){
			log.warn("Income annual statements list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			IncomeStatementReportSynchronizationAdminAssembler assembler = new IncomeStatementReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyIncomeStatementQuarterReportSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherIncomeStatementDataFor(tickerSymbol, syncState).getQuarterlyReports(), 
									syncState
								)
					);
		
		} catch (EmptyListException e){
			log.warn("Income quarterly statements list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);	
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void persistReports(List<IncomeStatementReport> synchronizeReports) {
		for(IncomeStatementReport item : synchronizeReports) {
			repository.store(item);
		}
		
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

	

	
}
