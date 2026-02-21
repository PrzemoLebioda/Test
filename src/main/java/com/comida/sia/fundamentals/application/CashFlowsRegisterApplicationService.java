package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.cashflow.CashFlowReportsSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowReport;
import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowsDataAcquirer;
import com.comida.sia.fundamentals.port.application.cashflow.CashFlowsRegisterService;
import com.comida.sia.fundamentals.port.repository.CashFlowRepository;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CashFlowsRegisterApplicationService")
public class CashFlowsRegisterApplicationService extends AssertionConcern implements CashFlowsRegisterService, Notifier {
	
	@Autowired
	private CashFlowRepository repository;
	
	@Autowired
	private CashFlowsDataAcquirer acquirer;

	@Autowired
	protected CashFlowReportsSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			
			CashFlowReportSynchronizationAdminAssembler assembler = new CashFlowReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyCashFlowAnnualReportSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherCashFlowsDataFor(tickerSymbol,syncState).getAnnualReports(), 
									syncState
								)
						);
			
		} catch (EmptyListException e){
			log.warn("Cash flow annual reports list for " + tickerSymbol + " is empty");
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
		
			CashFlowReportSynchronizationAdminAssembler assembler = new CashFlowReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyCashFlowQuarterReportSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherCashFlowsDataFor(tickerSymbol, syncState).getQuarterlyReports(),
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Cash flow quarterly reports list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	private void persistReports(List<CashFlowReport> synchronizeReports) {
		for(CashFlowReport item : synchronizeReports) {
			repository.store(item);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}	
}
