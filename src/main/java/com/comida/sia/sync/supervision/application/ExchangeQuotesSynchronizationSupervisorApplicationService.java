package com.comida.sia.sync.supervision.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuoteSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.application.exchangequote.ExchangeQuotesSynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.repository.ExchangeQuoteSynchronizationSupervisorRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("ExchangeQuotesSynchronizationSupervisorApplicationService")
@PropertySource({"classpath:application.properties"})
public class ExchangeQuotesSynchronizationSupervisorApplicationService implements ExchangeQuotesSynchronizationSupervisorService, Notifier{

	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("ExchangeQuoteSynchronizationNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("ExchangeQuoteSynchronizationSupervisorHibernateRepository")
	private ExchangeQuoteSynchronizationSupervisorRepository repository;
	
	@Override
	@Transactional
	public void register(String tickerSymbol, AssetType assetType) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to register new adjusted exchange quotetions synchronization supervisor");
		ExchangeQuoteSynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get(tickerSymbol);
			
			if(supervisor == null) {
				supervisor = getSupervisor(tickerSymbol, assetType);
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();				
			} else {
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();
			}
			
			persist(supervisor);
		} catch (Exception e) {
			supervisor = getSupervisor(tickerSymbol, assetType);
			supervisor.registerAllMissingSyncStates();
			supervisor.activateSynchronization();
			
			persist(supervisor);
		}
	}

	@Override
	@Transactional
	public void deactivateSynchronization(String tickerSymbol, AssetType assetType) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to deactivate synchronization for company synchronization supervisor");
		ExchangeQuoteSynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get(tickerSymbol);
			
			if(supervisor == null) {
				supervisor = getSupervisor(tickerSymbol, assetType);
				supervisor.registerAllMissingSyncStates();
				supervisor.deactivateSynchronization();				
			} else {
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();
			}
			
			persist(supervisor);
		} catch (Exception e) {
			supervisor = getSupervisor(tickerSymbol, assetType);
			supervisor.registerAllMissingSyncStates();
			supervisor.deactivateSynchronization();
			
			persist(supervisor);
		}
	}

	private ExchangeQuoteSynchronizationSupervisor getSupervisor(String tickerSymbol, AssetType assetType) {
		return new ExchangeQuoteSynchronizationSupervisor(
						UUID.randomUUID(), 
						tickerSymbol, 
						assetType);
	}
	
	@Override
	@Transactional
	public void orderSynchronization() {
		for(ExchangeQuoteSynchronizationSupervisor supervisor : repository.getAll()) {
			try {
				supervisor
					.with(this)
					.with(env)
					.orderSynchronization();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	@Override
	@Transactional
	public void orderSynchronization(String tickerSymbol) {		
		try {
			ExchangeQuoteSynchronizationSupervisor supervisor = repository.get(tickerSymbol);
			AssertionConcern.assertNotNull(supervisor, "Can't order synchronization for ticker: " + tickerSymbol + " due to lack of dedicated supervisor");
			
			supervisor
				.with(this)
				.with(env)
				.orderSynchronization();
		} catch (Exception e) {
			
		}
	}

	@Override
	@Transactional
	public void calculateCurrentSyncState(Subject subject, String tickerSymbol, SynchronizationSummary summary) {
		try {
			ExchangeQuoteSynchronizationSupervisor supervisor = repository.get(tickerSymbol);
			AssertionConcern.assertNotNull(supervisor, "Can't calculate current sync state for ticker: " + tickerSymbol + " due to lack of dedicated supervisor");
			AssertionConcern.assertNotNull(subject, "Can't calculate current sync state for ticker: " + tickerSymbol + " due to lack of subject");		
			
			supervisor
				.with(this)
				.updateSyncState(subject, summary);
			
			persist(supervisor);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(ExchangeQuoteSynchronizationSupervisor supervisor) {
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
