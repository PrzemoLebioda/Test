package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.sharesoutstanding.SharesOutstandingSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.sharesoutstanding.SharesOutstandingReport;
import com.comida.sia.fundamentals.domain.model.sharesoutstanding.SharesOutstandingReportSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingDataAcquirer;
import com.comida.sia.fundamentals.port.application.sharesoutstanding.SharesOutstandingService;
import com.comida.sia.fundamentals.port.repository.SharesOutstandingRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("SharesOutstandingApplicationService")
public class SharesOutstandingApplicationService implements SharesOutstandingService, Notifier {

	@Autowired
	private SharesOutstandingRepository repository;
	
	@Autowired
	private SharesOutstandingDataAcquirer acquirer;
	
	@Autowired
	private SharesOutstandingSynchronizedNotificationPublisher publisher;

	@Override
	@Transactional
	public void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			SharesOutstandingReportSynchronizationAdminAssembler assembler = new SharesOutstandingReportSynchronizationAdminAssembler(tickerSymbol);							
			persistReports(
				assembler
					.assemblySharesOutstandingReportSynchronizationAdmin(syncState, this)
					.synchronize(
							acquirer.gatherSharesOutstandingData(tickerSymbol, syncState).getData(), 
							syncState)
			);
		} catch (EmptyListException e){
			log.warn("Shares outstanding list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persistReports(List<SharesOutstandingReport> synchronizeReports) {
		for(SharesOutstandingReport item : synchronizeReports) {
			repository.store(item);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
