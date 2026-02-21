package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.earnings.estimation.EarningEstimatesSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReport;
import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningsEstimatesDataAcquirer;
import com.comida.sia.fundamentals.port.application.earnings.estimates.EarningEstimatesReportService;
import com.comida.sia.fundamentals.port.repository.EarningEstimatesReportRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("EarningEstimatesReportApplicationService")
public class EarningEstimatesReportApplicationService implements EarningEstimatesReportService, Notifier{

	@Autowired
	private EarningEstimatesReportRepository repository;
	
	@Autowired
	private EarningsEstimatesDataAcquirer acquirer;
	
	@Autowired
	private EarningEstimatesSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeReports(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			EarningEstimatesReportSynchronizationAdminAssembler assembler = new EarningEstimatesReportSynchronizationAdminAssembler(tickerSymbol);
			
			persistReports(
					assembler
						.assemblyEarningEstimatesReportSynchronizationAdmin(syncState, this)
							.synchronize(
									acquirer.gatherEarningsEstimatesDataFor(tickerSymbol, syncState).getEstimates(), 
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Earnings estimates list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void persistReports(List<EarningEstimatesReport> synchronizeReports) {
		for(EarningEstimatesReport item : synchronizeReports) {
			repository.store(item);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
