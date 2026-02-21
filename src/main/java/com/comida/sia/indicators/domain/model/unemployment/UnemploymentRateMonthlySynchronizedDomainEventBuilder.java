package com.comida.sia.indicators.domain.model.unemployment;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class UnemploymentRateMonthlySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, UnemploymentRate>{

	public UnemploymentRateMonthlySynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, UnemploymentRate> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build unemployment rate synchronized domain event");
		
		return new UnemploymentRateMonthlySynchronizedDomainEvent(report.getSummary());
	}

}
