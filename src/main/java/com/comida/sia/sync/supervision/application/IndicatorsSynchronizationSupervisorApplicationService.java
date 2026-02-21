package com.comida.sia.sync.supervision.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.indicators.IndicatorsSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.application.indicators.IndicatorsSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.repository.IndicatorsSynchronizationSypervisorRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("IndicatorsSynchronizationSupervisorApplicationService")
@PropertySource({"classpath:application.properties"})
public class IndicatorsSynchronizationSupervisorApplicationService implements IndicatorsSynchronizationSupervisorService, Notifier {

	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("IndicatorsSynchronizationSypervisorHibernateRepository")
	private IndicatorsSynchronizationSypervisorRepository repository;
	
	@Autowired
	@Qualifier("IndicatorsSynchronizationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	@Transactional
	public void orderSynchronization() {
		try {
			getSupervisor()
				.with(this)
				.with(env)
				.orderSynchronization();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void calculateCurrentSyncState(Subject subject, SynchronizationSummary summary) {
		try {
			IndicatorsSynchronizationSupervisor supervisor = getSupervisor();
			
			supervisor
				.with(this)
				.updateSyncState(subject, summary);
			
			persist(supervisor);
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private IndicatorsSynchronizationSupervisor getSupervisor() {
		IndicatorsSynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get();
			if(supervisor == null) {
				supervisor = new IndicatorsSynchronizationSupervisor(UUID.randomUUID());
			}
			supervisor.registerAllMissingSyncStates();
			return supervisor;
		} catch(Exception e) {
			supervisor = new IndicatorsSynchronizationSupervisor(UUID.randomUUID());
			supervisor.registerAllMissingSyncStates();
			return supervisor;
		}
	}
	
	private void persist(IndicatorsSynchronizationSupervisor supervisor) {
		try {
			repository.update(supervisor);
		} catch (Exception e) {
			repository.store(supervisor);
		}
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

}
