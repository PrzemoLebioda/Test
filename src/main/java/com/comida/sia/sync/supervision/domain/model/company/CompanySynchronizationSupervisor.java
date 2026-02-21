package com.comida.sia.sync.supervision.domain.model.company;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.domain.DomainEntity;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.AssetSynchronizationSupervisor;
import com.comida.sia.sync.supervision.domain.model.ContinousSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SheduledSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="CompanySynchronizationSupervisor")
@DiscriminatorValue("COMPANY")
public class CompanySynchronizationSupervisor extends AssetSynchronizationSupervisor implements DomainEntity<CompanySynchronizationSupervisor> {
		
	private Date ipoDate;
	
	public CompanySynchronizationSupervisor() {
		super();
	}
	
	public CompanySynchronizationSupervisor(UUID id, String tickerSymbol, AssetType assetType) {
		super(id, tickerSymbol, assetType);
	}
	
	public CompanySynchronizationSupervisor(UUID id, String tickerSymbol, Date ipoDate, AssetType assetType) {
		super(id, tickerSymbol, assetType);
		setIpoDate(ipoDate);
	}
	
	public void setIpoDate(Date ipoDate) {
		AssertionConcern.assertNotNull(ipoDate, "IPO date must be provided");
		this.ipoDate = ipoDate;
	}
	
	@Override
	public void registerAllMissingSyncStates() throws ParseException {
		registerKeyMetricsSyncState();
		registerBalanceSheetAnnualReportSyncState();
		registerBalanceSheetQuarterReportSyncState();
		registerCashFlowAnnualReportSyncState();
		registerCashFlowQuarterReportSyncState();
		registerEarningsAnnualReportSyncState();
		registerEarningsQuarterReportSyncState();
		registerIncomeAnnualReportSyncState();
		registerIncomeQuarterReportSyncState();
		registerSharesOutstandingReportSyncState();
		registerEarningsEstimatesReportSyncState();
		
		registerDividendEventSyncState();
		registerSplitEventSyncState();
		
		registerInsiderTransactionsSyncState();
		
		registerOptionsSyncState();
	}
	
	private void registerSplitEventSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.SPLIT_EVENT));
	}

	private void registerDividendEventSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.DIVIDEND_EVENT));
	}

	private void registerEarningsEstimatesReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING,
				new InfinitePeriod(),
				SynchronizationScope.EARNING_ESTIMATES_REPORT));
	}

	private void registerSharesOutstandingReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.SHARES_OUTSTANDING_REPORT));
	}

	private void registerIncomeQuarterReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INCOME_STATEMENT_QUARTER_REPORT));
	}

	private void registerIncomeAnnualReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.INCOME_STATEMENT_ANNUAL_REPORT));
	}

	private void registerEarningsQuarterReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING,
				new InfinitePeriod(), 
				SynchronizationScope.EARNINGS_QUARTER_REPORT));
	}

	private void registerEarningsAnnualReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.EARNINGS_ANNUAL_REPORT));	
	}

	private void registerCashFlowQuarterReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.CASH_FLOW_QUARTER_REPORT));
	}

	private void registerCashFlowAnnualReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.CASH_FLOW_ANNUAL_REPORT));	
	}

	private void registerBalanceSheetQuarterReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.BALANCE_SHEET_QUARTER_REPORT));
	}

	private void registerBalanceSheetAnnualReportSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.BALANCE_SHEET_ANNUAL_REPORT));
	}

	private void registerKeyMetricsSyncState() {
		register(new SheduledSynchronizationState(
					UUID.randomUUID(), 
					Direction.ASCENDING, 
					new InfinitePeriod(), 
					SynchronizationScope.COMPANY_KEY_METRICS
					)
				);
	}
	
	private void registerInsiderTransactionsSyncState() {
		register(new SheduledSynchronizationState(
					UUID.randomUUID(), 
					Direction.ASCENDING, 
					new InfinitePeriod(), 
					SynchronizationScope.INSIDERS_TRANSACTIONS
				)
			);
	}
	
	private void registerOptionsSyncState() throws ParseException {
		SynchronizationState syncState = new ContinousSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.OPTIONS
			);
		
		syncState.setStartSyncTime(
				ComparationConcern.getMax(
						ipoDate, 
						TranslationConcern.getDateFrom("2008-01-01")
					)
				);
		
		register(syncState);
	}
	
	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		
		getSyncStateMap().get(SynchronizationScope.COMPANY_KEY_METRICS).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.BALANCE_SHEET_ANNUAL_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.BALANCE_SHEET_QUARTER_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.CASH_FLOW_ANNUAL_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.CASH_FLOW_QUARTER_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.EARNINGS_ANNUAL_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.EARNINGS_QUARTER_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.INCOME_STATEMENT_ANNUAL_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.INCOME_STATEMENT_QUARTER_REPORT).schedule(nextSyncDate);
		getSyncStateMap().get(SynchronizationScope.EARNING_ESTIMATES_REPORT).schedule(nextSyncDate);
	}

	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new CompanySubjectMapper();
		}
		
		return subjectMapper;
	}

	@Override
	public void orderSynchronization() throws NumberFormatException, InterruptedException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new CompanySyncTaggedDomainEventAnnouncer(notifier, syncState, getTickerSymbol()))
				.synchronize();
		}
	}

	@Override
	public boolean sameIdentityAs(CompanySynchronizationSupervisor other) {
		if(other == null) {
			return false;
		}
		
		return getId().equals(other.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanySynchronizationSupervisor other = (CompanySynchronizationSupervisor) obj;
		return sameIdentityAs(other);
	}

	@Override
	public String toString() {
		try {
			return "CompanySynchronizationSupervisor [getSubjectMapper()=" + getSubjectMapper() + ", getTickerSymbol()="
					+ getTickerSymbol() + ", getAssetType()=" + getAssetType() + ", getSynchronizationStatus()="
					+ getSynchronizationStatus() + ", getSyncStateMap()=" + getSyncStateMap() + ", getId()=" + getId()
					+ "]";
		} catch(Exception e) {
			return "CompanySynchronizationSupervisor [getSubjectMapper()=" + getSubjectMapper() + ", getTickerSymbol()="
					+ getTickerSymbol() + ", getAssetType()=" + getAssetType() + ", getSynchronizationStatus()="
					+ getSynchronizationStatus() + ", getId()=" + getId()
					+ "]";
		}
		
	}

}
