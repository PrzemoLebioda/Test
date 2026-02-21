package com.comida.sia.fundamentals.domain.model.income;

import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class IncomeStatementQuarterReportSynchronizedDomainEventBuilder implements DomainEventBuilder<IncomeStatementReportData, IncomeStatementReport> {

	public IncomeStatementQuarterReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IncomeStatementReportData, IncomeStatementReport> report) {
		return new IncomeStatementQuarterReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
