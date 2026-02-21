package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SyncDomainEventAnnouncer;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class IndicatorsSyncDomainEventAnnouncer extends SyncDomainEventAnnouncer {

	public IndicatorsSyncDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState) {
		super(notifier, syncState);
	}

	@Override
	public void announce() {
		AssertionConcern.assertNotNull(notifier, "Notifier is neccary in order to send a domain event");
		AssertionConcern.assertNotNull(syncState, "Synchronization state is mandatory in order to provide necessary information for synchronization");
		
		notifier.notify(getDomainEvent());
	}

	private SubjectedPayload getDomainEvent() {		
		switch(syncState.getScope()) {
			case GDP_ANNUAL:
				return new AnnualGdpSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case GDP_QUARTER:
				return new QuarterlyGdpSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case GDP_PER_CAPITA_ANNUAL:
				return new AnnualGdpPerCapitaSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case GDP_PER_CAPITA_QUARTER:
				return new QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_03_MONTH:
				return new TreasuryYealdDaily03MonthSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_02_YEAR:
				return new TreasuryYealdDaily02YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_05_YEAR:
				return new TreasuryYealdDaily05YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_07_YEAR:
				return new TreasuryYealdDaily07YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_10_YEAR:
				return new TreasuryYealdDaily10YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_DAILY_30_YEAR:
				return new TreasuryYealdDaily30YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_03_MONTH:
				return new TreasuryYealdMonthly03MonthSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_02_YEAR:
				return new TreasuryYealdMonthly02YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_05_YEAR:
				return new TreasuryYealdMonthly05YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_07_YEAR:
				return new TreasuryYealdMonthly07YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_10_YEAR:
				return new TreasuryYealdMonthly10YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_MONTHLY_30_YEAR:
				return new TreasuryYealdMonthly30YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_03_MONTH:
				return new TreasuryYealdWeekly03MonthSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_02_YEAR:
				return new TreasuryYealdWeekly02YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_05_YEAR:
				return new TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_07_YEAR:
				return new TreasuryYealdWeekly07YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_10_YEAR:
				return new TreasuryYealdWeekly10YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case TREASURY_YEALD_WEEKLY_30_YEAR:
				return new TreasuryYealdWeekly30YearSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case INTREST_RATE_MONTHLY:
				return new IntrestRateMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case INTREST_RATE_WEEKLY:
				return new IntrestRateWeeklySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case INTREST_RATE_DAILY:
				return new IntrestRateDailySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case CPI_MONTHLY:
				return new CpiMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case CPI_SEMIANNUAL:
				return new CpiSemiannualSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case INFLATION_ANNUAL:
				return new InflationAnnualSynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case RETAIL_SALES_MONTHLY:
				return new RetailSalesMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case DURABLE_GOODS_ORDERS_MONTHLY:
				return new DurableGoodsOrdersMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case UNEMPLOYMENT_RATE_MONTHLY:
				return new UnemploymentRateMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			case NONFARM_PAYROLL_MONTHLY:
				return new NonfarmPayrolMonthlySynchronizationOrderedDomainEvent(new SynchronizationStateDto(syncState));
			default:
				throw new IllegalArgumentException("Subject: " + syncState.getScope() + " not supported!");
		}
	}
}
