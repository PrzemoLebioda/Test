package com.comida.sia.fundamentals.domain.model.cashflow;

import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class CashFlowAnnualReportSynchronizedDomainEventBuilder implements DomainEventBuilder<CashFlowReportData, CashFlowReport>{

	public CashFlowAnnualReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<CashFlowReportData, CashFlowReport> report) {
		return new CashFlowAnnualReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
