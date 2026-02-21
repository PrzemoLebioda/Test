package com.comida.sia.indicators.domain.model.cpi;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class CpiMonthlySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, ConsumerPriceIndex>{

	public CpiMonthlySynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, ConsumerPriceIndex> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build cpi synchronized domain event");
		
		return new CpiMonthlySynchronizedDomainEvent(report.getSummary());
	}

}
