package com.comida.sia.fundamentals.domain.model.stock;

import com.comida.sia.fundamentals.port.acquirer.stock.StockData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class DelistedStockSynchronizedDomainEventBuilder implements DomainEventBuilder<StockData, Stock> {

	public DelistedStockSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<StockData, Stock> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build stock synchronized domain event");
		
		return new DelistedStockSynchronizedDomainEvent(report.getSummary());
	}

}
