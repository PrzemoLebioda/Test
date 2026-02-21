package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.balancesheet.BalanceSheetReportsSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetReport;
import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetsDataAcquirer;
import com.comida.sia.fundamentals.port.application.balancesheet.BalanceSheetsRegisterService;
import com.comida.sia.fundamentals.port.repository.BalanceSheetRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("BalanceSheetsRegisterApplicationService")
public class BalanceSheetsRegisterApplicationService implements BalanceSheetsRegisterService, Notifier {
	
	@Autowired
	private BalanceSheetRepository repository;
	
	@Autowired
	private BalanceSheetsDataAcquirer acquirer;
	
	@Autowired
	protected BalanceSheetReportsSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState) {		
		try {
			BalanceSheetReportSynchronizationAdminAssembler assembler = new BalanceSheetReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyBalanceSheetAnnualReportSyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherBalanceSheetsDataFor(tickerSymbol, syncState).getAnnualReports(), 
								syncState
							)
					);
		} catch (EmptyListException e){
			log.warn("Balance sheet annual reports list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			
			BalanceSheetReportSynchronizationAdminAssembler assembler = new BalanceSheetReportSynchronizationAdminAssembler(tickerSymbol);
		
			persistReports(
					assembler
						.assemblyBalanceSheetQuarterReportSyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherBalanceSheetsDataFor(tickerSymbol, syncState).getQuarterlyReports(), 
								syncState
							)
					);
		} catch (EmptyListException e){
			log.warn("Balance sheet quarterly reports list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	private void persistReports(List<BalanceSheetReport> synchronizeReports) {
		for(BalanceSheetReport item : synchronizeReports) {
			repository.store(item);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

}
