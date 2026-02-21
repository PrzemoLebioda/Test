package com.comida.sia.options.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.port.application.CompanyDataService;
import com.comida.sia.options.domain.model.Option;
import com.comida.sia.options.domain.model.OptionsSynchronizationAdminAssembler;
import com.comida.sia.options.port.acquirer.OptionAcquirer;
import com.comida.sia.options.port.application.OptionService;
import com.comida.sia.options.port.repository.OptionRepository;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("OptionApplicationService")
public class OptionApplicationService implements OptionService, Notifier{
	
	@Autowired
	@Qualifier("OptionHibernateRepository")
	private OptionRepository repository;
	
	@Autowired
	@Qualifier("AlfavantageOptionAcquirer")
	private OptionAcquirer acquirer;
	
	@Autowired
	@Qualifier("OptionsNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("CompanyDataApplicationService")
	CompanyDataService companyDataService;

	@Override
	@Transactional
	public void registerOption(String tickerSymbol, SynchronizationStateDto syncState) {
		try {		
			OptionsSynchronizationAdminAssembler assembler = new OptionsSynchronizationAdminAssembler(tickerSymbol);
			
			persist(assembler
					.assemblyOptionsSyncAdmin(syncState, this)
					.synchronize(
							acquirer.gatherOptionDataFor(
									tickerSymbol, 
									ComparationConcern.getMax(
											syncState.getStartSyncTime(), 
											syncState.getLastSyncedEventOccuranceTime()
										)), 
							syncState),
					gatherCompany(tickerSymbol)
				);			
			
		} catch (EmptyListException e){
			log.warn("Options for " + tickerSymbol + " list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private Company gatherCompany(String tickerSymbol) {
		AssertionConcern.assertNotNull(tickerSymbol, "Ticker symbol must be provided in order to gather company data");
		Company company = companyDataService.getCompany(tickerSymbol);
		
		AssertionConcern.assertNotNull(company, "Company must exists");
		return company;
	}
	
	private void persist(List<Option> options, Company company){
		for(Option item : options) {
			enrich(item, company);
			repository.store(item);
		}
	}
	
	private void enrich(Option option, Company company) {
		option.setIndustry(company.getIndustry());
		option.setSector(company.getSector());
	}
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
	
	
}
