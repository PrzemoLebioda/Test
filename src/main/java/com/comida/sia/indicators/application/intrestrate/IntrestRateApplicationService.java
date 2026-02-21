package com.comida.sia.indicators.application.intrestrate;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.indicators.domain.model.intrestrate.IntrestRate;
import com.comida.sia.indicators.domain.model.intrestrate.IntrestRateSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.IndicatorsData;
import com.comida.sia.indicators.port.acquirer.IndicatorsDataAcquirer;
import com.comida.sia.indicators.port.application.intrestrate.IntrestRateService;
import com.comida.sia.indicators.port.repository.intrestrate.IntrestRateRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("IntrestRateApplicationService")
public class IntrestRateApplicationService implements IntrestRateService, Notifier{

	@Autowired
	@Qualifier("AlfavantageIntrestRateDataAcquirer")
	private IndicatorsDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("IntrestRateHibernateRepository")
	public IntrestRateRepository repository;
	
	@Autowired
	@Qualifier("IntrestRateNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	@Transactional
	public void synchronizeMonthlyIntrestRate(SynchronizationStateDto syncState) {
		try {
			IndicatorsData monthlyIntrestRateData = this.acquirer.gatherIndicatorData(Interval.MONTHLY);
			IntrestRateSynchronizationAdminAssembler assembler = new IntrestRateSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyIntrestRateMonthlySyncAdmin(syncState, this)
					.synchronize(monthlyIntrestRateData.getData(), syncState)
			);
		} catch (EmptyListException e){
			log.warn("Intrest rate monthly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeWeeklyIntrestRate(SynchronizationStateDto syncState) {
		try {
			IndicatorsData weeklyIntrestRateData = this.acquirer.gatherIndicatorData(Interval.WEEKLY);
			IntrestRateSynchronizationAdminAssembler assembler = new IntrestRateSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyIntrestRateWeeklySyncAdmin(syncState, this)
					.synchronize(weeklyIntrestRateData.getData(), syncState)
			);
		} catch (EmptyListException e){
			log.warn("Intrest rate weekly list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeDailyIntrestRate(SynchronizationStateDto syncState) {
		try {
			IndicatorsData dailyIntrestRateData = this.acquirer.gatherIndicatorData(Interval.DAILY);		
			IntrestRateSynchronizationAdminAssembler assembler = new IntrestRateSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyIntrestRateWeeklySyncAdmin(syncState, this)
					.synchronize(dailyIntrestRateData.getData(), syncState)
			);
		} catch (EmptyListException e){
			log.warn("Intrest rate daily list is empty");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<IntrestRate> intrestRateList) {
		for(IntrestRate intrestRate : intrestRateList) {
			persist(intrestRate);
		}
	}

	private void persist(IntrestRate intrestRate) {
		try {
			repository.store(intrestRate);
		} catch(Exception e) {
			repository.update(intrestRate);
		}
	}

	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
}
