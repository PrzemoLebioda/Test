package com.comida.sia.fundamentals.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.corpoevents.earnings.EarningsCalendarNotificationsPublisher;
import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.CorporateEarningEvent;
import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.CorporateEarningEventSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningCalendarDataAcquirer;
import com.comida.sia.fundamentals.port.application.corpoevents.earnings.EarningsCalendarService;
import com.comida.sia.fundamentals.port.repository.CorporateEarningsEventRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("EarningsCalendarApplicationService")
public class EarningsCalendarApplicationService implements EarningsCalendarService, Notifier{

	@Autowired
	private CorporateEarningsEventRepository repository;
	
	@Autowired
	private EarningCalendarDataAcquirer acquirer;
	
	@Autowired
	private EarningsCalendarNotificationsPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeEvents(SynchronizationStateDto syncState) {
		try {
			CorporateEarningEventSynchronizationAdminAssembler assembler = new CorporateEarningEventSynchronizationAdminAssembler();
			persist(
					assembler
						.assemblyCorporateEarningEventSyncAdmin(syncState, this)
						.synchronize(acquirer.gatherEarningCalendarData(syncState), syncState));
		} catch (EmptyListException e){
			log.warn("Corporate earnings events list is empty");
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}	
	}
	
	private void persist(List<CorporateEarningEvent> events) {
		for(CorporateEarningEvent event : events) {
			try {
				repository.update(event);
			} catch(Exception e) {
				repository.store(event);
				
			}
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
	
	
}
