package com.comida.sia.fundamentals.domain.model.corpoevents.splits;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class CorporateSplitEventSynchronizedDomainEventBuilder implements DomainEventBuilder<SplitEventData, CorporateSplitEvent> {

	public CorporateSplitEventSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<SplitEventData, CorporateSplitEvent> report) {
		return new CorporateSplitEventSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

	
}
