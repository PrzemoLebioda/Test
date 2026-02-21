package com.comida.sia.indicators.domain.model.durables;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class DurableGoodsOrdersMonthlySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, DurableGoodsOrders> {

	public DurableGoodsOrdersMonthlySynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, DurableGoodsOrders> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build durables synchronized domain event");
		
		return new DurableGoodsOrdersMonthlySynchronizedDomainEvent(report.getSummary());
	}

}
