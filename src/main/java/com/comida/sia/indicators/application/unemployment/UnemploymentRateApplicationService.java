package com.comida.sia.indicators.application.unemployment;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.adapter.repository.unemployment.UnemploymentRateHibernateRepository;
import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.unemployment.UnemploymentRate;
import com.comida.sia.indicators.domain.model.unemployment.UnemploymentRateSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.unemployment.UnemploymentRateService;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("UnemploymentRateApplicationService")
public class UnemploymentRateApplicationService implements UnemploymentRateService, Notifier {

	@Autowired
	@Qualifier("AlfavantageUnemploymentRateDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("UnemploymentRateHibernateRepository")
	private UnemploymentRateHibernateRepository repository;
	
	@Autowired
	@Qualifier("UnemploymentRateNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeMonthly(SynchronizationStateDto syncState) {
		try {		
			UnemploymentRateSynchronizationAdminAssembler assembler = new UnemploymentRateSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyUnemploymentRateMonthlySyncAdmin(syncState, this)
					.synchronize(
							acquirer.gatherIndicatorData(Interval.MONTHLY).getData(), 
							syncState
						)
			);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<UnemploymentRate> unemploymentRateList) {
		for(UnemploymentRate unemploymentRate : unemploymentRateList) {
			persist(unemploymentRate);
		}
	}

	private void persist(UnemploymentRate unemploymentRate) {
		try {
			repository.store(unemploymentRate);
		} catch(Exception e) {
			repository.update(unemploymentRate);
		}
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

}
