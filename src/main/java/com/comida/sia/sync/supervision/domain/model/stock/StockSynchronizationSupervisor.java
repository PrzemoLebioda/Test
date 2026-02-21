package com.comida.sia.sync.supervision.domain.model.stock;
 
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SheduledSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSupervisor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="StockSynchronizationSupervisor")
@DiscriminatorValue("STOCK")
public class StockSynchronizationSupervisor extends SynchronizationSupervisor {
	
	public StockSynchronizationSupervisor() {
		super();
	}
	
	public StockSynchronizationSupervisor(UUID id) {
		super(id);
	}

	@Override
	public void registerAllMissingSyncStates() {
		registerStockListedSyncState();
		registerStockDelistedSyncState();
	}
	
	private void registerStockListedSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.LISTING_SYNC
			)
		);
	}
	
	private void registerStockDelistedSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.DELISTING_SYNC
			)
		);
	}
	
	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		getSyncStateMap().get(SynchronizationScope.LISTING_SYNC).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.DELISTING_SYNC).schedule(nextSyncDate);
	}

	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new StockSubjectMapper();
		}
		
		return subjectMapper;
	}

	@Override
	public void orderSynchronization() throws NumberFormatException, InterruptedException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new StockSyncDomainEventAnnouncer(notifier, syncState))
				.synchronize();
		}
	}

}
