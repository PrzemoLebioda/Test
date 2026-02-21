package com.comida.sia.fundamentals.domain.model.earnings;

import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsQuarterlyReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class EarningsQuarterReportSynchronizedDomainEventBuilder implements DomainEventBuilder<EarningsQuarterlyReportData, EarningReport> {

	public EarningsQuarterReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<EarningsQuarterlyReportData, EarningReport> report) {
		return new EarningsQuarterReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

	

}
