package com.comida.sia.sync.supervision.domain.model.company.events;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class BalanceSheetAnnualReportSyncOrderedDomainEvent extends SyncTickerTaggedDomainEvent{		
	
	public BalanceSheetAnnualReportSyncOrderedDomainEvent(
			String tickerSymbol,
			SynchronizationStateDto syncState) {
		
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.BALANCE_SHEET_ANNUAL_REPORT_SYNC_ORDERED;
	}
}
