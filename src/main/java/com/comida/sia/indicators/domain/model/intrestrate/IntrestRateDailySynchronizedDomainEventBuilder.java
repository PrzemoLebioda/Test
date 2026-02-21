package com.comida.sia.indicators.domain.model.intrestrate;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class IntrestRateDailySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, IntrestRate> {

	public IntrestRateDailySynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, IntrestRate> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build intrest rate synchronized domain event");
		
		return new IntrestRateDailySynchronizedDomainEvent(report.getSummary());
	}

}
