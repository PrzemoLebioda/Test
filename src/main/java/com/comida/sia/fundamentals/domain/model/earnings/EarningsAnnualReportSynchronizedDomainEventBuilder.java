package com.comida.sia.fundamentals.domain.model.earnings;

import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsAnnualReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class EarningsAnnualReportSynchronizedDomainEventBuilder implements DomainEventBuilder<EarningsAnnualReportData, EarningReport>{

	public EarningsAnnualReportSynchronizedDomainEventBuilder() {
		super();
	}

	@Override
	public SubjectedPayload build(SynchronizationReport<EarningsAnnualReportData, EarningReport> report) {
		return new EarningsAnnualReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}
}
