package com.comida.sia.indicators.application.gdp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.gdp.GdpSynchronizationAdminAssembler;
import com.comida.sia.indicators.domain.model.gdp.GrossDomesticProductPerCapita;
import com.comida.sia.indicators.port.acquirer.IndicatorsData;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.gdp.GdpService;
import com.comida.sia.indicators.port.repository.gdp.GdpPerCapitaRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("GdpPerCapitaApplicationService")
public class GdpPerCapitaApplicationService implements GdpService, Notifier {

	@Autowired
	@Qualifier("AlfavantageGdpPerCapitaDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("GdpNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Autowired
	@Qualifier("GdpPerCapitaHibernateRepository")
	public GdpPerCapitaRepository repository;
	
	@Override
	@Transactional
	public void synchronizeAnnualGdp(SynchronizationStateDto syncState) {
		try {
			IndicatorsData annualGdpData = this.acquirer.gatherIndicatorData(Interval.ANNUAL);
			GdpSynchronizationAdminAssembler assembler = new GdpSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyGdpPerCapitaAnnualSyncAdmin(syncState, this)
						.synchronize(annualGdpData.getData(), syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("GDP per Capita annual list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	}

	@Override
	@Transactional
	public void synchronizeQuarterlyGdp(SynchronizationStateDto syncState) {
		try {
			IndicatorsData quarterlyGdpData = this.acquirer.gatherIndicatorData(Interval.QUARTERLY);
			GdpSynchronizationAdminAssembler assembler = new GdpSynchronizationAdminAssembler();
			
			persist(
					assembler
						.assemblyGdpPerCapitaQuarterSyncAdmin(syncState, this)
						.synchronize(quarterlyGdpData.getData(), syncState)
			);
			
		} catch (EmptyListException e){
			log.warn("GDP per Capita quarterly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<GrossDomesticProductPerCapita> gdpList) {
		for(GrossDomesticProductPerCapita gdp : gdpList) {
			persist(gdp);
		}
	}
	
	private void persist(GrossDomesticProductPerCapita gdp) {
		try {
			repository.store(gdp);
		} catch(Exception e) {
			repository.update(gdp);
		}
	}
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}

}
