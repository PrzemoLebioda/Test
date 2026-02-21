package com.comida.sia.sync.supervision.domain.model.company;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SyncTaggedDomainEventAnnouncer;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.BalanceSheetQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarDividendSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.CalendarSplitsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.CashflowAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.CashflowQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.CompanyKeyMetricsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsEstimatesSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.EarningsQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.IncomeAnnualReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.IncomeQuarterReportSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.InsidersTransactionsSynchronizationOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.OptionsSyncOrderedDomainEvent;
import com.comida.sia.sync.supervision.domain.model.company.events.SharesOutstandingReportSyncOrderedDomainEvent;

public class CompanySyncTaggedDomainEventAnnouncer extends SyncTaggedDomainEventAnnouncer{

	public CompanySyncTaggedDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState, String tag) {
		super(notifier, syncState, tag);
	}

	@Override
	public void announce() {
		AssertionConcern.assertNotEmpty(tag, "Tag (eg. ticekr symbol) is mandatory in order to announce occurane of domain event");
		AssertionConcern.assertNotNull(notifier, "Notifier is neccary in order to send a domain event");
		AssertionConcern.assertNotNull(syncState, "Synchronization state is mandatory in order to provide necessary information for synchronization");
		
		notifier.notify(getDomainEvent());
	}
	
	private SubjectedPayload getDomainEvent() {
		switch(syncState.getScope()) {
			case COMPANY_KEY_METRICS:
				return new CompanyKeyMetricsSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case BALANCE_SHEET_ANNUAL_REPORT:
				return new BalanceSheetQuarterReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case BALANCE_SHEET_QUARTER_REPORT:
				return new BalanceSheetAnnualReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case CASH_FLOW_ANNUAL_REPORT:
				return new CashflowAnnualReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case CASH_FLOW_QUARTER_REPORT:
				return new CashflowQuarterReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EARNINGS_ANNUAL_REPORT:
				return new EarningsAnnualReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EARNINGS_QUARTER_REPORT:
				return new EarningsQuarterReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case INCOME_STATEMENT_ANNUAL_REPORT:
				return new IncomeAnnualReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case INCOME_STATEMENT_QUARTER_REPORT:
				return new IncomeQuarterReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case SHARES_OUTSTANDING_REPORT:
				return new SharesOutstandingReportSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case EARNING_ESTIMATES_REPORT:
				return new EarningsEstimatesSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case DIVIDEND_EVENT:
				return new CalendarDividendSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case SPLIT_EVENT:
				return new CalendarSplitsSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case INSIDERS_TRANSACTIONS:
				return new InsidersTransactionsSynchronizationOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			case OPTIONS:
				return new OptionsSyncOrderedDomainEvent(tag, new SynchronizationStateDto(syncState));
			default:
				throw new IllegalArgumentException("Subject: " + syncState.getScope() + " not supported!");
		}
	} 

}
