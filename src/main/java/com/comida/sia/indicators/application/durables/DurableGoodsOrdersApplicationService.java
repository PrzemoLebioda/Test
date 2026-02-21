package com.comida.sia.indicators.application.durables;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.durables.DurableGoodsOrders;
import com.comida.sia.indicators.domain.model.durables.DurablesGoodsOrdersSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.durables.DurableGoodsOrdersService;
import com.comida.sia.indicators.port.repository.durables.DurableGoodsOrdersRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("DurableGoodsOrdersApplicationService")
public class DurableGoodsOrdersApplicationService implements DurableGoodsOrdersService, Notifier{

	@Autowired
	@Qualifier("AlfavantageDurableGoodsOrdersDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("DurableGoodsOrdersHibernateRepository")
	private DurableGoodsOrdersRepository repository;
	
	@Autowired
	@Qualifier("DurableGoodsOrdersNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeMonthly(SynchronizationStateDto syncState) {
		try {
			DurablesGoodsOrdersSynchronizationAdminAssembler assembler = new DurablesGoodsOrdersSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyDurablesMonthlySyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherIndicatorData(Interval.MONTHLY).getData(),
								syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("Durable goods monthly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	}

	private void persist(List<DurableGoodsOrders> durablesList) {
		for(DurableGoodsOrders gdp : durablesList) {
			persist(gdp);
		}
	}

	private void persist(DurableGoodsOrders durable) {
		try {
			repository.store(durable);
		} catch(Exception e) {
			repository.update(durable);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
