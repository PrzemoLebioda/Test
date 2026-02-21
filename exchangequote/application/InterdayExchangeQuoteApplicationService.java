package com.comida.sia.exchangequote.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.ExchangeQuote;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday01Min;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday05Min;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday15Min;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday30Min;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteInterday60Min;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteSynchronizationAdminAssembler;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotesInterdayAcquirer;
import com.comida.sia.exchangequote.port.acquirer.Interval;
import com.comida.sia.exchangequote.port.application.InterdayExchangeQuoteService;
import com.comida.sia.exchangequote.port.repository.ExchangeQuotesRepository;
import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.port.application.CompanyDataService;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sharedkernel.period.MonthlyPeriod;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("InterdayExchangeQuoteApplicationService")
public class InterdayExchangeQuoteApplicationService implements InterdayExchangeQuoteService, Notifier{

	@Autowired
	ExchangeQuotesRepository repository;
	
	@Autowired
	@Qualifier("AlfavantageExchangeQuotesInterdayAcquirer")
	private ExchangeQuotesInterdayAcquirer acquirer;
	
	@Autowired
	@Qualifier("InterdayExchangeQuoteNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("CompanyDataApplicationService")
	CompanyDataService companyDataService;
	
	@Override
	public void synchronizeInterday60MinQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			AssertionConcern.assertNotNull(syncState.getStartSyncTime(), "Synchtonization start time is madatory in order to run interday synchronization");
			Period period = new MonthlyPeriod(
					ComparationConcern.getMax(
							syncState.getStartSyncTime(), 
							syncState.getLastSyncedEventOccuranceTime()
						)
				);
			
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedInterday60MinQuotations(
						assembler
							.assemblyExchangeQuoteInterday60SyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherExchangeQuoteFor(tickerSymbol, Interval.SIXTY_MIN_INTERVAL, period.getFormatted()), 
									syncState),
						gatherCompany(tickerSymbol)
					);
		
		} catch (EmptyListException e){
			log.warn("Interday 60 min interval quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void synchronizeInterday30MinQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			AssertionConcern.assertNotNull(syncState.getStartSyncTime(), "Synchtonization start time is madatory in order to run interday synchronization");
			Period period = new MonthlyPeriod(
					ComparationConcern.getMax(
							syncState.getStartSyncTime(), 
							syncState.getLastSyncedEventOccuranceTime()
						)
				);
			
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedInterday30MinQuotations(
						assembler
							.assemblyExchangeQuoteInterday30SyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherExchangeQuoteFor(tickerSymbol, Interval.THIRTY_MIN_INTERVAL, period.getFormatted()), 
									syncState),
						gatherCompany(tickerSymbol)
					);
		
		} catch (EmptyListException e){
			log.warn("Interday 30 min interval quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void synchronizeInterday15MinQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			AssertionConcern.assertNotNull(syncState.getStartSyncTime(), "Synchtonization start time is madatory in order to run interday synchronization");
			Period period = new MonthlyPeriod(
					ComparationConcern.getMax(
							syncState.getStartSyncTime(), 
							syncState.getLastSyncedEventOccuranceTime()
						)
				);
			
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedInterday15MinQuotations(
						assembler
							.assemblyExchangeQuoteInterday15SyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherExchangeQuoteFor(tickerSymbol, Interval.FIFTEEN_MIN_INTERVAL, period.getFormatted()), 
									syncState),
						gatherCompany(tickerSymbol)
					);
		
		} catch (EmptyListException e){
			log.warn("Interday 15 min interval quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void synchronizeInterday05MinQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			AssertionConcern.assertNotNull(syncState.getStartSyncTime(), "Synchtonization start time is madatory in order to run interday synchronization");
			Period period = new MonthlyPeriod(
					ComparationConcern.getMax(
							syncState.getStartSyncTime(), 
							syncState.getLastSyncedEventOccuranceTime()
						)
				);
			
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedInterday05MinQuotations(
						assembler
							.assemblyExchangeQuoteInterday05SyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherExchangeQuoteFor(tickerSymbol, Interval.FIVE_MIN_INTERVAL, period.getFormatted()), 
									syncState),
						gatherCompany(tickerSymbol)
					);
		
		} catch (EmptyListException e){
			log.warn("Interday 05 min interval quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void synchronizeInterday01MinQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			AssertionConcern.assertNotNull(syncState.getStartSyncTime(), "Synchtonization start time is madatory in order to run interday synchronization");
			Period period = new MonthlyPeriod(
					ComparationConcern.getMax(
							syncState.getStartSyncTime(), 
							syncState.getLastSyncedEventOccuranceTime()
						)
				);
			
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedInterday01MinQuotations(
						assembler
							.assemblyExchangeQuoteInterday01SyncAdmin(syncState, this)
							.synchronize(
									acquirer.gatherExchangeQuoteFor(tickerSymbol, Interval.ONE_MIN_INTERVAL, period.getFormatted()), 
									syncState),
						gatherCompany(tickerSymbol)
					);
		
		} catch (EmptyListException e){
			log.warn("Interday 01 min interval quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private Company gatherCompany(String tickerSymbol) {
		AssertionConcern.assertNotNull(tickerSymbol, "Ticker symbol must be provided in order to gather company data");
		Company company = companyDataService.getCompany(tickerSymbol);
		
		AssertionConcern.assertNotNull(company, "Company must exists");
		return company;
	}
	
	private void persistEnreachedInterday60MinQuotations(List<ExchangeQuoteInterday60Min> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}

	private void persistEnreachedInterday30MinQuotations(List<ExchangeQuoteInterday30Min> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}
	
	private void persistEnreachedInterday15MinQuotations(List<ExchangeQuoteInterday15Min> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}
	
	private void persistEnreachedInterday05MinQuotations(List<ExchangeQuoteInterday05Min> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}
	
	private void persistEnreachedInterday01MinQuotations(List<ExchangeQuoteInterday01Min> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}
	
	private void persist(ExchangeQuote exchangeQuote) {
		try {
			repository.store(exchangeQuote);
		} catch(Exception e) {
			repository.update(exchangeQuote);
		}
	}
	
	private void enrich(ExchangeQuote exchangeQuote, Company company) {
		exchangeQuote.setTickerSymbol(company.getTickerSymbol());
		exchangeQuote.setExchange(company.getExchange());
		exchangeQuote.setIndustry(company.getIndustry());
		exchangeQuote.setSector(company.getSector());
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
