package com.comida.sia.fundamentals.domain.model.balancesheet;

import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class BalanceSheetAnnualReportSynchronizedDomainEventBuilder implements DomainEventBuilder<BalanceSheetReportData, BalanceSheetReport> {
	
	public BalanceSheetAnnualReportSynchronizedDomainEventBuilder() {
		super();
	}

	@Override
	public SubjectedPayload build(SynchronizationReport<BalanceSheetReportData, BalanceSheetReport> report) {
		return new BalanceSheetAnnualReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
