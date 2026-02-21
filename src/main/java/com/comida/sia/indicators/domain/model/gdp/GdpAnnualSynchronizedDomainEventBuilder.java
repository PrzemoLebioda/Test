package com.comida.sia.indicators.domain.model.gdp;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class GdpAnnualSynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, GrossDomesticProduct>{

	public GdpAnnualSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, GrossDomesticProduct> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build gdp synchronized domain event");
		
		return new GdpAnnualSynchronizedDomainEvent(report.getSummary());
	}

}
