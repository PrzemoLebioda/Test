package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.corpoevents.splits.CorporateSplitEventSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.corpoevents.splits.CorporateSplitEvent;
import com.comida.sia.fundamentals.domain.model.corpoevents.splits.CorporateSplitEventSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventDataAcquirer;
import com.comida.sia.fundamentals.port.application.corpoevents.splits.CorporateSplitEventService;
import com.comida.sia.fundamentals.port.repository.CorporateSplitEventRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CorporateSplitEventApplicationService")
public class CorporateSplitEventApplicationService implements CorporateSplitEventService, Notifier {

	@Autowired
	private CorporateSplitEventRepository repository;
	
	@Autowired
	private SplitEventDataAcquirer acquirer;
	
	@Autowired
	private CorporateSplitEventSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			CorporateSplitEventSynchronizationAdminAssembler assembler = new CorporateSplitEventSynchronizationAdminAssembler(tickerSymbol);
			
			persistEvents(
					assembler
						.assemblyCorporateSplitEventSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherSplitCalendarData(tickerSymbol, syncState).getData(), 
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Corporate split events list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persistEvents(List<CorporateSplitEvent> synchronizeReports) {
		for(CorporateSplitEvent item : synchronizeReports) {
			repository.store(item);
		}
		
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
