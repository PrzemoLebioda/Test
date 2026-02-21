package com.comida.sia.fundamentals.domain.model.balancesheet;

import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class BalanceSheetQuarterReportSynchronizedDomainEventBuilder implements DomainEventBuilder<BalanceSheetReportData, BalanceSheetReport> {

	public BalanceSheetQuarterReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<BalanceSheetReportData, BalanceSheetReport> report) {
		return new BalanceSheetQuarterReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
