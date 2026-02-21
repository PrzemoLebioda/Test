package com.comida.sia.sync.supervision.domain.model.indicators;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SheduledSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSupervisor;
import com.comida.sia.sync.supervision.domain.model.indicators.events.IndicatorsSyncDomainEventAnnouncer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="IndicatorsSynchronizationSupervisor")
@DiscriminatorValue("INDICATORS")
public class IndicatorsSynchronizationSupervisor extends SynchronizationSupervisor{

	public IndicatorsSynchronizationSupervisor() {
		super();
	}
	
	public IndicatorsSynchronizationSupervisor(UUID id) {
		super(id);
	}
	
	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new IndicatorsSubjectMapper();
		}
		
		return subjectMapper;
	}
	
	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			syncState.schedule(nextSyncDate);
		}
	}

	@Override
	public void orderSynchronization() throws NumberFormatException, InterruptedException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new IndicatorsSyncDomainEventAnnouncer(notifier, syncState))
				.synchronize();
		}
	}
	
	@Override
	public void updateSyncState(Subject subject, SynchronizationSummary summary) throws ParseException {
		AssertionConcern.assertNotNull(subject, "Subject is madatory in order to update synchronization state");
		SynchronizationState syncState = getSyncStateMap().get(getSubjectMapper().getSyncStateTypeFrom(subject));
		
		syncState
			.with(new IndicatorsSyncDomainEventAnnouncer(notifier, syncState))
			.updateWith(summary);
	}
	
	@Override
	public void registerAllMissingSyncStates() {
		registerAnnualGdpSyncState();
		registerQuarterlyGdpSyncState();
		
		registerAnnualPerCapitaGdpSyncState();
		registerQuarterlyPerCapitaGdpSyncState();
		
		registerTreasuryYealdDaily02YearSyncState();
		registerTreasuryYealdDaily03MonthSyncState();
		registerTreasuryYealdDaily05YearSyncState();
		registerTreasuryYealdDaily07YearSyncState();
		registerTreasuryYealdDaily10YearSyncState();
		registerTreasuryYealdDaily30YearSyncState();
		
		registerTreasuryYealdWeekly02YearSyncState();
		registerTreasuryYealdWeekly03MonthSyncState();
		registerTreasuryYealdWeekly05YearSyncState();
		registerTreasuryYealdWeekly07YearSyncState();
		registerTreasuryYealdWeekly10YearSyncState();
		registerTreasuryYealdWeekly30YearSyncState();
		
		registerTreasuryYealdMonthly02YearSyncState();
		registerTreasuryYealdMonthly03MonthSyncState();
		registerTreasuryYealdMonthly05YearSyncState();
		registerTreasuryYealdMonthly07YearSyncState();
		registerTreasuryYealdMonthly10YearSyncState();
		registerTreasuryYealdMonthly30YearSyncState();
		
		registerIntrestRateMonthlySyncState();
		registerIntrestRateWeeklySyncState();
		registerIntrestRateDailySyncState();
		
		registerCpiMonthlySyncState();
		registerCpiSemiannualSyncState();
		
		registerInflationAnnualSyncState();
		
		registerRetailSalesMonthlySyncState();
		
		registerDurableGoodsOrderMonthlySyncState();
		
		registerUnemploymentMonthlySyncState();
		
		registerNonfarmPayrollMonthlySyncState();
	}

	private void registerIntrestRateDailySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INTREST_RATE_DAILY
			)
		);
	}

	private void registerIntrestRateWeeklySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INTREST_RATE_WEEKLY
			)
		);
	}

	private void registerIntrestRateMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INTREST_RATE_MONTHLY
			)
		);
	}

	private void registerAnnualGdpSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.GDP_ANNUAL
			)
		);
	}
	
	private void registerQuarterlyGdpSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.GDP_QUARTER
			)
		);
	}
	
	private void registerAnnualPerCapitaGdpSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.GDP_PER_CAPITA_ANNUAL
			)
		);
	}
	
	private void registerQuarterlyPerCapitaGdpSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.GDP_PER_CAPITA_QUARTER
			)
		);
	}
	
	private void registerTreasuryYealdMonthly30YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_30_YEAR
			)
		);
	}

	private void registerTreasuryYealdMonthly10YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_10_YEAR
			)
		);
	}

	private void registerTreasuryYealdMonthly07YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_07_YEAR
			)
		);
	}

	private void registerTreasuryYealdMonthly05YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_05_YEAR
			)
		);
	}

	private void registerTreasuryYealdMonthly03MonthSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_03_MONTH
			)
		);
	}

	private void registerTreasuryYealdMonthly02YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_MONTHLY_02_YEAR
			)
		);
	}

	private void registerTreasuryYealdWeekly30YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_30_YEAR
			)
		);
	}

	private void registerTreasuryYealdWeekly10YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_10_YEAR
			)
		);
	}

	private void registerTreasuryYealdWeekly07YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_07_YEAR
			)
		);
	}

	private void registerTreasuryYealdWeekly05YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_05_YEAR
			)
		);
	}

	private void registerTreasuryYealdWeekly03MonthSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_03_MONTH
			)
		);
	}

	private void registerTreasuryYealdWeekly02YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_WEEKLY_02_YEAR
			)
		);
	}

	private void registerTreasuryYealdDaily30YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_30_YEAR
			)
		);
	}

	private void registerTreasuryYealdDaily10YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_10_YEAR
			)
		);
	}

	private void registerTreasuryYealdDaily07YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_07_YEAR
			)
		);
	}

	private void registerTreasuryYealdDaily05YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_05_YEAR
			)
		);
	}

	private void registerTreasuryYealdDaily03MonthSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_03_MONTH
			)
		);
	}
	
	private void registerTreasuryYealdDaily02YearSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.TREASURY_YEALD_DAILY_02_YEAR
			)
		);
	}
	
	private void registerCpiMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.CPI_MONTHLY
			)
		);
	}
	
	private void registerCpiSemiannualSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.CPI_SEMIANNUAL
			)
		);
	}
	
	private void registerInflationAnnualSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INFLATION_ANNUAL
			)
		);
	}
	
	private void registerRetailSalesMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.RETAIL_SALES_MONTHLY
			)
		);
	}
	
	private void registerDurableGoodsOrderMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.DURABLE_GOODS_ORDERS_MONTHLY
			)
		);
	}
	
	private void registerUnemploymentMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.UNEMPLOYMENT_RATE_MONTHLY
			)
		);
	}
	
	private void registerNonfarmPayrollMonthlySyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.NONFARM_PAYROLL_MONTHLY
			)
		);
	}
}
