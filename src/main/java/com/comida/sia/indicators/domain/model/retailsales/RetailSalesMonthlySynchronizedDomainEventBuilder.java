package com.comida.sia.indicators.domain.model.retailsales;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class RetailSalesMonthlySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, RetailSales> {

	public RetailSalesMonthlySynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, RetailSales> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build intrest rate synchronized domain event");
		
		return new RetailSalesMonthlySynchronizedDomainEvent(report.getSummary());
	}
}
