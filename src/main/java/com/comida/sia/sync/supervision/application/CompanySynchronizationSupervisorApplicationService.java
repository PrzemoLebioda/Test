package com.comida.sia.sync.supervision.application;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.adapter.messaging.company.CompanySynchronizationNotificationPublisher;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;
import com.comida.sia.sync.supervision.domain.model.company.CompanySynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.application.company.CompanySynchronizationSupervisorService;
import com.comida.sia.sync.supervision.port.repository.CompanySynchronizationSypervisorRepository;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CompanySynchronizationSupervisorApplicationService")
@PropertySource({"classpath:application.properties"})
public class CompanySynchronizationSupervisorApplicationService implements CompanySynchronizationSupervisorService, Notifier {
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("CompanySynchronizationSypervisorHibernateRepository") 
	private CompanySynchronizationSypervisorRepository repository;
	
	@Autowired
	private CompanySynchronizationNotificationPublisher publisher;
	
	@Override
	@Transactional
	public void register(String tickerSymbol, Date ipoDate, AssetType assetType) throws ParseException {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to register new company synchronization supervisor");
		CompanySynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get(tickerSymbol);
			
			if(supervisor == null) {
				supervisor = getSupervisor(tickerSymbol, ipoDate, assetType);
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();				
			} else {
				supervisor.setIpoDate(ipoDate);
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();
			}
			
			persist(supervisor);
		} catch (Exception e) {
			supervisor = getSupervisor(tickerSymbol, ipoDate, assetType);
			supervisor.registerAllMissingSyncStates();
			supervisor.activateSynchronization();
			
			persist(supervisor);
		}
	}
	
	@Override
	@Transactional
	public void deactivateSynchronization(String tickerSymbol, Date ipoDate, AssetType assetType) throws ParseException {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to deactivate synchronization for company synchronization supervisor");
		CompanySynchronizationSupervisor supervisor = null;
		
		try {
			supervisor = repository.get(tickerSymbol);
			
			if(supervisor == null) {
				supervisor = getSupervisor(tickerSymbol, ipoDate, assetType);
				supervisor.registerAllMissingSyncStates();
				supervisor.deactivateSynchronization();				
			} else {
				supervisor.setIpoDate(ipoDate);
				supervisor.registerAllMissingSyncStates();
				supervisor.activateSynchronization();
			}
			
			persist(supervisor);
		} catch (Exception e) {
			supervisor = getSupervisor(tickerSymbol, ipoDate, assetType);
			supervisor.registerAllMissingSyncStates();
			supervisor.deactivateSynchronization();
			
			persist(supervisor);
		}
	}
	
	private CompanySynchronizationSupervisor getSupervisor(String tickerSymbol, Date ipoDate, AssetType assetType) {
		return new CompanySynchronizationSupervisor(
						UUID.randomUUID(), 
						tickerSymbol, 
						ipoDate,
						assetType);
	}

	@Override
	@Transactional
	public void orderSynchronization() {
		try {
			for(CompanySynchronizationSupervisor supervisor : repository.getAll()) {
				supervisor
					.with(this)
					.with(env)
					.orderSynchronization();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional
	public void orderSynchronization(String tickerSymbol) {
		try {
			CompanySynchronizationSupervisor supervisor = repository.get(tickerSymbol);
			AssertionConcern.assertNotNull(supervisor, "Can't order synchronization for ticker: " + tickerSymbol + " due to lack of dedicated supervisor");
			
			supervisor
				.with(this)
				.with(env)
				.orderSynchronization();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void calculateCurrentSyncState(Subject subject, String tickerSymbol, SynchronizationSummary summary) {
		try {
			CompanySynchronizationSupervisor supervisor = repository.get(tickerSymbol);
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
	
	private void persist(CompanySynchronizationSupervisor supervisor) {
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

	@Override
	@Transactional
	public void sheduleSynchronization(String tickerSymbol, Date planedEarningsReleaseDate) {
		try {
			CompanySynchronizationSupervisor supervisor = repository.get(tickerSymbol);
			AssertionConcern.assertNotNull(supervisor, "Can't calculate current sync state for ticker: " + tickerSymbol + " due to lack of dedicated supervisor");
			
			supervisor.scheduleSynchronization(planedEarningsReleaseDate);
			
			persist(supervisor);
		} catch(NoResultException e) {
			log.warn("Can't calculate current sync state for ticker: " + tickerSymbol + " due to lack of dedicated supervisor");
		} catch(Exception e) {
			log.error(tickerSymbol + ": " + e.getMessage(), e);
		}
		
	}
}
