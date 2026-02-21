package com.comida.sia.sync.supervision.domain.model.exchangequote;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.AssetSynchronizationSupervisor;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SheduledSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="ExchangeQuoteSynchronizationSupervisor")
@DiscriminatorValue("EXCHANGE_QUOTE")
public class ExchangeQuoteSynchronizationSupervisor extends AssetSynchronizationSupervisor{
	
	public ExchangeQuoteSynchronizationSupervisor() {
		super();
	}
	
	public ExchangeQuoteSynchronizationSupervisor(UUID id, String tickerSymbol, AssetType assetType) {
		super(id, tickerSymbol, assetType);
	}
	
	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new ExchangeQuoteSubjectMapper();
		}
		
		return subjectMapper;
	}

	@Override
	public void orderSynchronization() throws InterruptedException, NumberFormatException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new ExchangeQuotesSyncDomainEventAnnouncer(notifier, syncState, getTickerSymbol()))
				.synchronize();
		}
	}

	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			schedule(syncState, nextSyncDate);
		}
	}
	
	private void schedule(SynchronizationState syncState, Date nextSyncDate) {
		scheduleDailyAdjustedExchangeQuote(syncState, nextSyncDate);
		scheduleWeeklyAdjustedExchangeQuote(syncState, nextSyncDate);
		scheduleMonthlyAdjustedExchangeQuote(syncState, nextSyncDate);
	}
	
	private void scheduleDailyAdjustedExchangeQuote(SynchronizationState syncState, Date nextSyncDate) {
		if(SynchronizationScope.EXCHANGE_QUOTE_DAILY.equals(syncState.getScope())) {
			syncState.schedule(nextSyncDate);
		}
	}
	
	private void scheduleWeeklyAdjustedExchangeQuote(SynchronizationState syncState, Date nextSyncDate) {
		if(SynchronizationScope.EXCHANGE_QUOTE_WEEKLY.equals(syncState.getScope())) {
			LocalDateTime localDateTime = LocalDateTime.ofInstant(nextSyncDate.toInstant(), ZoneId.systemDefault());
			
			syncState.schedule(
					Date.from(
							localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
								.atZone(ZoneId.systemDefault())
								.toInstant()
						)
				);
		}
	}
	
	private void scheduleMonthlyAdjustedExchangeQuote(SynchronizationState syncState, Date nextSyncDate) {
		if(SynchronizationScope.EXCHANGE_QUOTE_MONTHLY.equals(syncState.getScope())) {
			LocalDateTime localDateTime = LocalDateTime.ofInstant(nextSyncDate.toInstant(), ZoneId.systemDefault());
			
			syncState.schedule(
					Date.from(
							localDateTime.with(TemporalAdjusters.lastDayOfMonth())
								.atZone(ZoneId.systemDefault())
								.toInstant()
						)
				);
		}
	}
	
	@Override
	public void registerAllMissingSyncStates() {
		registerDailyAdjustedExchangeQuoteSyncState();
		registerWeeklyAdjustedExchangeQuoteSyncState();
		registerMonthlyAdjustedExchangeQuoteSyncState();
	}

	private void registerMonthlyAdjustedExchangeQuoteSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.EXCHANGE_QUOTE_MONTHLY
			)
		);
	}

	private void registerWeeklyAdjustedExchangeQuoteSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.EXCHANGE_QUOTE_WEEKLY
			)
		);
	}

	private void registerDailyAdjustedExchangeQuoteSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.EXCHANGE_QUOTE_DAILY
			)
		);
	}
}
