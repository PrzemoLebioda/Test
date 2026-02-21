package com.comida.sia.intelligence.insidertransactions.domain.model;

import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class InsiderTransactionsSynchronizedDomainEventBuilder implements DomainEventBuilder<InsiderTransactionData, InsiderTransaction> {

	public InsiderTransactionsSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<InsiderTransactionData, InsiderTransaction> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build stock synchronized domain event");
		
		return new InsiderTransactionsSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
