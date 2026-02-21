package com.comida.sia.indicators.application.inflation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.inflation.Inflation;
import com.comida.sia.indicators.domain.model.inflation.InflationSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.inflation.InflationService;
import com.comida.sia.indicators.port.repository.inflation.InflationRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("InflationApplicationService")
public class InflationApplicationService implements InflationService, Notifier{

	@Autowired
	@Qualifier("AlfavantageInflationDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("InflationNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("InflationHibernateRepository")
	public InflationRepository repository;
	
	@Override
	@Transactional
	public void synchronizeAnnualInflation(SynchronizationStateDto syncState) {
		try {
			InflationSynchronizationAdminAssembler assembler = new InflationSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyInflationAnnualSyncAdmin(syncState, this)
						.synchronize(
								acquirer.gatherIndicatorData(Interval.ANNUAL).getData(),
								syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("Inflation annual list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	}

	private void persist(List<Inflation> inflationList) {
		for(Inflation gdp : inflationList) {
			persist(gdp);
		}
	}

	private void persist(Inflation inflation) {
		try {
			repository.store(inflation);
		} catch(Exception e) {
			repository.update(inflation);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
