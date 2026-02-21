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
import com.comida.sia.sync.supervision.domain.model.stock.StockSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.application.stock.StockSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.repository.StockSynchronizationSypervisorRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("StockSynchronizationSupervisorApplicationService")
@PropertySource({"classpath:application.properties"})
public class StockSynchronizationSupervisorApplicationService implements StockSynchronizationSupervisorService, Notifier {
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("StockSynchronizationSypervisorHibernateRepository")
	private StockSynchronizationSypervisorRepository repository;
	
	@Autowired
	@Qualifier("StockSynchronizationPublisher")
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
	public void calculateListedStocksCurrentSyncState(SynchronizationSummary summary) {
		try {
			StockSynchronizationSupervisor supervisor = getSupervisor();
			supervisor
				.with(this)
				.updateSyncState(Subject.LISTED_STOCK_SYNCED, summary);
			persist(supervisor);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void calculateDelistedStocksCurrentSyncState(SynchronizationSummary summary) {
		try {
			StockSynchronizationSupervisor supervisor = getSupervisor();
			supervisor.updateSyncState(Subject.DELISTED_STOCK_SYNCED, summary);
			persist(supervisor);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private StockSynchronizationSupervisor getSupervisor() {
		StockSynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get();
			if(supervisor == null) {
				supervisor = new StockSynchronizationSupervisor(UUID.randomUUID());
			}
			supervisor.registerAllMissingSyncStates();
			return supervisor;
		} catch(Exception e) {
			supervisor = new StockSynchronizationSupervisor(UUID.randomUUID());
			supervisor.registerAllMissingSyncStates();
			return supervisor;
		}
	}
	
	private void persist(StockSynchronizationSupervisor supervisor) {
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
