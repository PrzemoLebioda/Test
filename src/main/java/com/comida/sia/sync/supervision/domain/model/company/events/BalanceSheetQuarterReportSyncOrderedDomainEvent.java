package com.comida.sia.sync.supervision.domain.model.company.events;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class BalanceSheetQuarterReportSyncOrderedDomainEvent extends SyncTickerTaggedDomainEvent {

	public BalanceSheetQuarterReportSyncOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.BALANCE_SHEET_QUARTER_REPORT_SYNC_ORDERED;
	}

}
