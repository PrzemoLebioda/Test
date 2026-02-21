package com.comida.sia.sync.supervision.domain.model.exchangequote;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.AssetSynchronizationSupervisor;
import com.comida.sia.sync.supervision.domain.model.ContinousSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="InterdayExchangeQuoteSynchronizationSupervisor")
@DiscriminatorValue("INTERDAY_EXCHANGE_QUOTE")
public class InterdayExchangeQuoteSynchronizationSupervisor extends AssetSynchronizationSupervisor {
	
	public InterdayExchangeQuoteSynchronizationSupervisor() {
		super();
	}
	
	public InterdayExchangeQuoteSynchronizationSupervisor(UUID id, String tickerSymbol, AssetType assetType) {
		super(id, tickerSymbol, assetType);
	}
	
	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new InterdayExchangeQuoteSubjectMapper();
		}
		
		return subjectMapper;
	}

	@Override
	public void orderSynchronization() throws InterruptedException, NumberFormatException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new InterdayExchangeQuotesSyncDomainEventAnnouncer(notifier, syncState, getTickerSymbol()))
				.synchronize();
		}
	}

	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			syncState.schedule(nextSyncDate);
		}
	}

	@Override
	public void registerAllMissingSyncStates() {
		registerInterday60MinSyncState();
		registerInterday30MinSyncState();
		registerInterday15MinSyncState();
		registerInterday05MinSyncState();
		registerInterday01MinSyncState();
	}
	
	private void registerInterday60MinSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 	//Sprawdzić, czy nie będzie trzeba zmienic rodzaju okresu
				SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_60_MIN
			)
		);
	}
	
	private void registerInterday30MinSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 	//Sprawdzić, czy nie będzie trzeba zmienic rodzaju okresu
				SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_30_MIN
			)
		);
	}
	
	private void registerInterday15MinSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 	//Sprawdzić, czy nie będzie trzeba zmienic rodzaju okresu
				SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_15_MIN
			)
		);
	}
	
	private void registerInterday05MinSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 	//Sprawdzić, czy nie będzie trzeba zmienic rodzaju okresu
				SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_05_MIN
			)
		);
	}
	
	private void registerInterday01MinSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 	//Sprawdzić, czy nie będzie trzeba zmienic rodzaju okresu
				SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_01_MIN
			)
		);
	}
	
	public void setStartSyncTime(Date startSyncTime) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			syncState.setStartSyncTime(startSyncTime);
		}
	}
}
