package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.corpoevents.dividend.CorporateDividendEventSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.corpoevents.dividend.CorporateDividendEvent;
import com.comida.sia.fundamentals.domain.model.corpoevents.dividend.CorporateDividendEventSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventDataAcquirer;
import com.comida.sia.fundamentals.port.application.corpoevents.dividend.CorporateDividendEventService;
import com.comida.sia.fundamentals.port.repository.CorporateDividendEventRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CorporateDividendEventApplicationService")
public class CorporateDividendEventApplicationService implements CorporateDividendEventService, Notifier{
		
	@Autowired
	private CorporateDividendEventRepository repository;
	
	@Autowired
	private DividendEventDataAcquirer acquirer;
	
	@Autowired
	private CorporateDividendEventSynchronizedNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeEvents(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			
			CorporateDividendEventSynchronizationAdminAssembler assembler = new CorporateDividendEventSynchronizationAdminAssembler(tickerSymbol);
			
			persistEvents(
					assembler
						.assemblyCorporateDividendEventSyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherDividendCalendarDataFor(tickerSymbol, syncState).getData(), 
									syncState
								)
						);
		} catch (EmptyListException e){
			log.warn("Corporate divident event list for " + tickerSymbol + " is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	private void persistEvents(List<CorporateDividendEvent> synchronizeReports) {
		for(CorporateDividendEvent item : synchronizeReports) {
			repository.store(item);
		}
		
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

	

}
