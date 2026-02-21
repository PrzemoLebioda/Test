package com.comida.sia.indicators.domain.model.inflation;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class InflationAnnualSynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, Inflation>{

	public InflationAnnualSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, Inflation> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build inflation synchronized domain event");
		return new InflationAnnualSynchronizedDomainEvent(report.getSummary());
	}

}
