package com.comida.sia.fundamentals.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.adapter.messaging.company.CompanyKeyMetricsSynchronizedNotificationPublisher;
import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.domain.model.company.CompanyDataTranslator;
import com.comida.sia.fundamentals.domain.model.company.CompanyKeyMetrics;
import com.comida.sia.fundamentals.domain.model.company.CompanyKeyMetricsSynchronizationAdminAssembler;
import com.comida.sia.fundamentals.domain.model.company.CompanyKeyMetricsTranslator;
import com.comida.sia.fundamentals.port.acquirer.company.CompanyDataAcquirer;
import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
import com.comida.sia.fundamentals.port.application.CompanyService;
import com.comida.sia.fundamentals.port.repository.CompanyRepository;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("CompanyApplicationService")
public class CompanyApplicationService extends AssertionConcern implements CompanyService, Notifier {
	
	@Autowired
	@Qualifier("CompanyHibernateRepository")
	private CompanyRepository repository;
	
	@Autowired
	private CompanyDataAcquirer acquirer;
	
	@Autowired
	private CompanyKeyMetricsSynchronizedNotificationPublisher publisher;
	
	private CompanyDataTranslator companyDataTranslator;

	@Override
	@Transactional
	public void synchronize(String tickerSymbol, SynchronizationStateDto syncState) {
		try {
			Company company = getCompany(tickerSymbol);
			CompanyDetailsData companyDetailsData = acquirer.gatherCompanyDetailsDataFor(tickerSymbol, syncState);
			CompanyKeyMetricsSynchronizationAdminAssembler assembler = new CompanyKeyMetricsSynchronizationAdminAssembler(tickerSymbol);
			SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics> keyMetricsSyncAdmin = assembler.assemblyCompanyKeyMetricsSynchronizationAdmin(syncState, this);
			
			if(notExists(company)) {
				company = getCompanyDataTranslator().translate(companyDetailsData);
			}
			
			List<CompanyKeyMetrics> synchronizedItems = keyMetricsSyncAdmin.synchronize(toList(companyDetailsData), syncState);
			
			for (CompanyKeyMetrics item : synchronizedItems) {
				company.registerKeyMetrics(item);
			}
			
			persist(company);
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private Company getCompany(String tickerSymbol) {
		try {
			return repository.get(tickerSymbol);
		} catch(Exception e) {
			return null;
		}
	}
	
	private Boolean notExists(Company company) {
		if(company == null) {
			return true;
		} else {
			return false;
		}
	}
	
	private CompanyDataTranslator getCompanyDataTranslator() {
		if(companyDataTranslator == null) {
			companyDataTranslator = new CompanyDataTranslator(); 
		}
		return companyDataTranslator;
	}
	
	private List<CompanyDetailsData> toList(CompanyDetailsData companyDetailsData){
		List<CompanyDetailsData> companyDetailsDataList = new ArrayList<>();
		companyDetailsDataList.add(companyDetailsData);
		return companyDetailsDataList;
	}
	
	private void persist(Company company) {
		try {
			repository.update(company);
		} catch(Exception e) {
			repository.store(company);
			
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
