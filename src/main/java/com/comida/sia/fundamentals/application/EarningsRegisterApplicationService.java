package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.earnings.EarningsReportsSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.earnings.EarningReport;
import com.comida.sia.fundamentals.domain.model.earnings.EarningsReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsDataAcquirer;
import com.comida.sia.fundamentals.port.application.earnings.EarningsRegisterService;
import com.comida.sia.fundamentals.port.repository.EarningsRepository;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("EarningsRegisterApplicationService")
public class EarningsRegisterApplicationService extends AssertionConcern implements EarningsRegisterService, Notifier {
	
	@Autowired
	private EarningsRepository repository;
	
	@Autowired
	private EarningsDataAcquirer acquirer;
	
	@Autowired
	private EarningsReportsSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			
			EarningsReportSynchronizationAdminAssembler assembler = new EarningsReportSynchronizationAdminAssembler(tickerSymbol);
						
			persistReports(
					assembler
						.assemblyEarningsAnnualReportSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherEarningsDataFor(tickerSymbol, syncState).getAnnualEarnings(), 
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Earnings annual reports list for " + tickerSymbol + " is empty");
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
			EarningsReportSynchronizationAdminAssembler assembler = new EarningsReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyEarningsQuarterReportSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherEarningsDataFor(tickerSymbol, syncState).getQuarterlyEarnings(), 
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Earnings quaterly reports list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void persistReports(List<EarningReport> synchronizeReports) {
		for(EarningReport item : synchronizeReports) {
			repository.store(item);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}	
}
