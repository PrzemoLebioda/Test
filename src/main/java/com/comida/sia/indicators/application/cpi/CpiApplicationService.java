package com.comida.sia.indicators.application.cpi;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.cpi.ConsumerPriceIndex;
import com.comida.sia.indicators.domain.model.cpi.CpiSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.cpi.CpiService;
import com.comida.sia.indicators.port.repository.cpi.CpiRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CpiApplicationService")
public class CpiApplicationService implements CpiService, Notifier{

	@Autowired
	@Qualifier("AlfavantageCpiDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("CpiNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("CpiHibernateRepository")
	public CpiRepository repository;

	@Override
	@Transactional
	public void synchronizeSemiannualCpi(SynchronizationStateDto syncState) {
		try {
			CpiSynchronizationAdminAssembler assembler = new CpiSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyCpiSemiannualSyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherIndicatorData(Interval.SEMIANNUAL).getData(),
								syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("CPI semiannual list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	}

	@Override
	@Transactional
	public void synchronizeMonthlyCpi(SynchronizationStateDto syncState) {
		try {
			CpiSynchronizationAdminAssembler assembler = new CpiSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyCpiMonthlySyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherIndicatorData(Interval.MONTHLY).getData(),
								syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("CPI monthly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	}
	
	private void persist(List<ConsumerPriceIndex> cpiList) {
		for(ConsumerPriceIndex gdp : cpiList) {
			persist(gdp);
		}
	}

	private void persist(ConsumerPriceIndex cpi) {
		try {
			repository.store(cpi);
		} catch(Exception e) {
			repository.update(cpi);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
