package com.comida.sia.fundamentals.domain.model.income;

import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class IncomeStatementAnnualReportSynchronizedDomainEventBuilder implements DomainEventBuilder<IncomeStatementReportData, IncomeStatementReport>{

	public IncomeStatementAnnualReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(
			SynchronizationReport<IncomeStatementReportData, IncomeStatementReport> report) {
		return new IncomeStatementAnnualReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
