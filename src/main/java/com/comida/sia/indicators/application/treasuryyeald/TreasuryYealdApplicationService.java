package com.comida.sia.indicators.application.treasuryyeald;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.indicators.domain.model.treasuryyeald.Interval;
import com.comida.sia.indicators.domain.model.treasuryyeald.Maturity;
import com.comida.sia.indicators.domain.model.treasuryyeald.TreasuryYeald;
import com.comida.sia.indicators.domain.model.treasuryyeald.TreasuryYealdSynchronizationAdminAssembler;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdDataAcquirer;
import com.comida.sia.indicators.port.application.treasuryyeald.TreasuryYealdService;
import com.comida.sia.indicators.port.repository.treasuryyeald.TreasuryYealdRepository;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sharedkernel.messaging.NotificationPublisher;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("TreasuryYealdApplicationService")
public class TreasuryYealdApplicationService implements TreasuryYealdService, Notifier {

	@Autowired
	@Qualifier("AlfavantageTreasuryYealdDataAcquirer")
	private TreasuryYealdDataAcquirer acquirer;
	
	@Autowired
	@Qualifier("TreasuryYealdHibernateRepository")
	private TreasuryYealdRepository repository;
	
	@Autowired
	@Qualifier("TreasuryYealdNotificationPublisher")
	private NotificationPublisher publisher;
	
	@Override
	public <P extends SubjectedPayload> void notify(P domainEvent) {
		Notification<P> notification = new Notification<>(UUID.randomUUID(), domainEvent);
		publisher.publish(notification);
	}
	
	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily02Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily02YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.TWO_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 2 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily03Month(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily03MonthSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.THREE_MONTH).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 3 months bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily05Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily05YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.FIVE_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 5 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily07Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily07YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.SEVEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 7 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily10Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily10YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.TEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 10 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdDaily30Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdDaily30YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.DAILY, Maturity.THIRTY_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald daily period for 30 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly02Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly02YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.TWO_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 2 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly03Month(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly03MonthSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.THREE_MONTH).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 3 month bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly05Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly05YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.FIVE_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 5 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly07Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly07YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.SEVEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 7 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly10Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly10YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.TEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 10 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdWeekly30Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdWeekly30YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.WEEKLY, Maturity.THIRTY_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald weekly period for 30 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly02Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly02YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.TWO_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 2 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly03Month(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly03MonthSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.THREE_MONTH).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 3 months bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly05Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly05YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.FIVE_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 5 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly07Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly07YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.SEVEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 7 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly10Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly10YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.TEN_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 10 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void synchronizeTreasuryYealdMonthly30Year(SynchronizationStateDto syncState) {
		try {
			TreasuryYealdSynchronizationAdminAssembler assembler = new TreasuryYealdSynchronizationAdminAssembler();
			
			persist(
				assembler
					.assemblyTreasuryYealdMonthly30YearSyncAdmin(syncState, this)
					.synchronize(
						acquirer.gatherGdpGeneral(Interval.MONTHLY, Maturity.THIRTY_YEAR).getData(), 
						syncState
					)
				);
		} catch (EmptyListException e){
			log.warn("Treasury yeald monthly period for 30 years bonds list is empty");
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void persist(List<TreasuryYeald> treasuryYealdList) {
		for(TreasuryYeald gdp : treasuryYealdList) {
			persist(gdp);
		}
	}
	
	private void persist(TreasuryYeald treasuryYeald) {
		try {
			repository.store(treasuryYeald);
		} catch(Exception e) {
			repository.update(treasuryYeald);
		}
	}

}
