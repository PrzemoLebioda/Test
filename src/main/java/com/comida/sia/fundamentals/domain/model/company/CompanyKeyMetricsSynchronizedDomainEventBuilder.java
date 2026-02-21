package com.comida.sia.fundamentals.domain.model.company;

import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class CompanyKeyMetricsSynchronizedDomainEventBuilder implements DomainEventBuilder<CompanyDetailsData, CompanyKeyMetrics>{

	public CompanyKeyMetricsSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<CompanyDetailsData, CompanyKeyMetrics> report) {
		return new CompanyKeyMetricsSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
