package com.comida.sia.sync.supervision.domain.model.company;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class CompanySubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case COMPANY_KEY_METRICS_SYNC_ORDERED:
				return SynchronizationScope.COMPANY_KEY_METRICS;
			case BALANCE_SHEET_ANNUAL_REPORT_SYNC_ORDERED:
				return SynchronizationScope.BALANCE_SHEET_ANNUAL_REPORT;
			case BALANCE_SHEET_QUARTER_REPORT_SYNC_ORDERED:
				return SynchronizationScope.BALANCE_SHEET_QUARTER_REPORT;
			case CASH_FLOW_ANNUAL_REPORT_SYNC_ORDERED:
				return SynchronizationScope.CASH_FLOW_ANNUAL_REPORT;
			case CASH_FLOW_QUARTER_REPORT_SYNC_ORDERED:
				return SynchronizationScope.CASH_FLOW_QUARTER_REPORT;
			case EARNINGS_ANNUAL_REPORT_SYNC_ORDERED:
				return SynchronizationScope.EARNINGS_ANNUAL_REPORT;
			case EARNINGS_QUARTER_REPORT_SYNC_ORDERED:
				return SynchronizationScope.EARNINGS_QUARTER_REPORT;
			case INCOME_ANNUAL_REPORT_SYNC_ORDERED:
				return SynchronizationScope.INCOME_STATEMENT_ANNUAL_REPORT;
			case INCOME_QUARTER_REPORT_SYNC_ORDERED:
				return SynchronizationScope.INCOME_STATEMENT_QUARTER_REPORT;
			case SHARES_OUTSTANDING_REPORT_SYNC_ORDERED:
				return SynchronizationScope.SHARES_OUTSTANDING_REPORT;
			case EARNINGS_ESTIMATES_SYNC_ORDERED:
				return SynchronizationScope.EARNING_ESTIMATES_REPORT;
			case CALENDAR_DIVIDEND_SYNC_ORDERED:
				return SynchronizationScope.DIVIDEND_EVENT;
			case CALENDAR_SPLITS_SYNC_ORDERED:
				return SynchronizationScope.SPLIT_EVENT;
			case INSIDER_TRANSACTIONS_SYNC_ORDERED:
				return SynchronizationScope.INSIDERS_TRANSACTIONS;	
			case OPTIONS_SYNC_ORDERED:
				return SynchronizationScope.OPTIONS;
		
			case BALANCE_SHEET_ANNUAL_REPORT_SYNCED:
				return SynchronizationScope.BALANCE_SHEET_ANNUAL_REPORT;
			case BALANCE_SHEET_QUARTER_REPORT_SYNCED:
				return SynchronizationScope.BALANCE_SHEET_QUARTER_REPORT;
			case CASH_FLOW_ANNUAL_REPORT_SYNCED:
				return SynchronizationScope.CASH_FLOW_ANNUAL_REPORT;
			case CASH_FLOW_QUARTER_REPORT_SYNCED:
				return SynchronizationScope.CASH_FLOW_QUARTER_REPORT;
			case EARNINGS_ANNUAL_REPORT_SYNCED:
				return SynchronizationScope.EARNINGS_ANNUAL_REPORT;
			case EARNINGS_QUARTER_REPORT_SYNCED:
				return SynchronizationScope.EARNINGS_QUARTER_REPORT;
			case INCOME_ANNUAL_REPORT_SYNCED:
				return SynchronizationScope.INCOME_STATEMENT_ANNUAL_REPORT;
			case INCOME_QUARTER_REPORT_SYNCED:
				return SynchronizationScope.INCOME_STATEMENT_QUARTER_REPORT;
			case COMPANY_KEY_METRICS_SYNCED:
				return SynchronizationScope.COMPANY_KEY_METRICS;
			case EARNINGS_ESTIMATES_SYNCED:
				return SynchronizationScope.EARNING_ESTIMATES_REPORT;
			case SHARES_OUTSTANDING_REPORT_SYNCED:
				return SynchronizationScope.SHARES_OUTSTANDING_REPORT;
			case CALENDAR_DIVIDEND_SYNCED:
				return SynchronizationScope.DIVIDEND_EVENT;
			case CALENDAR_SPLITS_SYNCED:
				return SynchronizationScope.SPLIT_EVENT;
			case INSIDER_TRANSACTIONS_SYNCED:
				return SynchronizationScope.INSIDERS_TRANSACTIONS;
			case OPTIONS_SYNCED:
				return SynchronizationScope.OPTIONS;
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
