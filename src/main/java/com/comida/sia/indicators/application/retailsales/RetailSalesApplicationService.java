package com.comida.sia.indicators.application.retailsales;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.retailsales.RetailSales;
import com.comida.sia.indicators.domain.model.retailsales.RetailSalesSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.retailsales.RetailSalesService;
import com.comida.sia.indicators.port.repository.retailsales.RetailSalesRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("RetailSalesApplicationService")
public class RetailSalesApplicationService implements RetailSalesService, Notifier{

	@Autowired
	@Qualifier("AlfavantageRetailSalesDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("RetailSalesHibernateRepository")
	public RetailSalesRepository repository;
	
	@Autowired
	@Qualifier("RetailSalesNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeMonthly(SynchronizationStateDto syncState) {
		try {		
			RetailSalesSynchronizationAdminAssembler assembler = new RetailSalesSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyRetailSalesMonthlySyncAdmin(syncState, this)
					.synchronize(
							acquirer.gatherIndicatorData(Interval.MONTHLY).getData(), 
							syncState
						)
			);
		} catch (EmptyListException e){
			log.warn("Retail sales monthly list is empty");
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void persist(List<RetailSales> retailSalesList) {
		for(RetailSales retailSales : retailSalesList) {
			persist(retailSales);
		}
	}

	private void persist(RetailSales retailSales) {
		try {
			repository.store(retailSales);
		} catch(Exception e) {
			repository.update(retailSales);
		}
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
	
}
