package com.comida.sia.exchangequote.application;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.exchangequote.domain.model.ExchangeQuote;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteDailyAdjusted;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteMonthlyAdjusted;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteSynchronizationAdminAssembler;
import com.comida.sia.exchangequote.domain.model.ExchangeQuoteWeeklyAdjusted;
import com.comida.sia.exchangequote.domain.model.QuotationService;
import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotesAcquirer;
import com.comida.sia.exchangequote.port.application.AdjustedExchangeQuoteService;
import com.comida.sia.exchangequote.port.repository.ExchangeQuotesRepository;
import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.port.application.CompanyDataService;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AdjustedExchangeQuoteApplicationService")
public class AdjustedExchangeQuoteApplicationService implements AdjustedExchangeQuoteService, Notifier {
	
	@Autowired
	ExchangeQuotesRepository repository;
	
	@Autowired
	@Qualifier("AdjustedExchangeQuoteNotificationPublisher")
	NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("AlfavantageExchangeQuotesDailyAcquirer")
	ExchangeQuotesAcquirer dailyQuotationsAcquirer;
	
	@Autowired
	@Qualifier("AlfavantageExchangeQuotesWeeklyAcquirer")
	ExchangeQuotesAcquirer weeklyQuotationsAcquirer;
	
	@Autowired
	@Qualifier("AlfavantageExchangeQuotesMonthlyAcquirer")
	ExchangeQuotesAcquirer monthlyQuotationsAcquirer;
	
	@Autowired
	@Qualifier("CompanyDataApplicationService")
	CompanyDataService companyDataService;

	@Override
	@Transactional
	public void synchronizeDailyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) throws ParseException, IOException, CsvException {
		try {
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedDailyQuotations(
					assembler
						.assemblyExchangeQuoteDailyAdjustedSyncAdmin(syncState, this)
						.synchronize(
								dailyQuotationsAcquirer.gatherExchangeQuoteFor(tickerSymbol),
								syncState),
					gatherCompany(tickerSymbol)
			);
		} catch (EmptyListException e){
			log.warn("Daily quotation list for " + tickerSymbol + " is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	@Override
	@Transactional
	public void synchronizeWeeklyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			
			persistEnreachedWeeklyQuotations(
					assembler
						.assemblyExchangeQuoteWeeklyAdjustedSyncAdmin(syncState, this)
						.synchronize(
								weeklyQuotationsAcquirer.gatherExchangeQuoteFor(tickerSymbol),
								syncState),
					gatherCompany(tickerSymbol)
			);
			
		} catch (EmptyListException e){
			log.warn("Weekly quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeMonthlyAdjustedQuotations(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			ExchangeQuoteSynchronizationAdminAssembler assembler = new ExchangeQuoteSynchronizationAdminAssembler(tickerSymbol);
			List<ExchangeQuoteMonthlyAdjusted> ExchangeQuoteMonthlyAdjustedList = assembler
					.assemblyExchangeQuoteMonthlyAdjustedSyncAdmin(syncState, this)
					.synchronize(
							monthlyQuotationsAcquirer.gatherExchangeQuoteFor(tickerSymbol),
							syncState);
			
			getQuotationService(tickerSymbol, ExchangeQuoteMonthlyAdjustedList).notifyFirstQuotationDate();
			
			persistEnreachedMonthlyQuotations(
					ExchangeQuoteMonthlyAdjustedList,
					gatherCompany(tickerSymbol)
			);
			
		} catch (EmptyListException e){
			log.warn("Monthly quotation list for " + tickerSymbol + " is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}	
	
	private <T extends ExchangeQuote> QuotationService<T> getQuotationService(String tickerSymbol, List<T> exchangeQuoteList) throws EmptyListException {
		return new QuotationService<T>(tickerSymbol, exchangeQuoteList, this);
	}
	
	private Company gatherCompany(String tickerSymbol) {
		AssertionConcern.assertNotNull(tickerSymbol, "Ticker symbol must be provided in order to gather company data");
		Company company = companyDataService.getCompany(tickerSymbol);
		
		AssertionConcern.assertNotNull(company, "Company must exists");
		return company;
	}
	
	private void persistEnreachedDailyQuotations(List<ExchangeQuoteDailyAdjusted> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}

	private void persistEnreachedWeeklyQuotations(List<ExchangeQuoteWeeklyAdjusted> exchangeQuoteList, Company company) {
		for(ExchangeQuote exchangeQuote : exchangeQuoteList) {
			enrich(exchangeQuote, company);
			persist(exchangeQuote);
		}
	}
	
	private void persistEnreachedMonthlyQuotations(List<ExchangeQuoteMonthlyAdjusted> exchangeQuoteList, Company company) {
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
