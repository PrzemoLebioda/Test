package com.comida.sia.fundamentals.domain.model.corpoevents.dividend;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class CorporateDividendEventSynchronizedDomainEventBuilder implements DomainEventBuilder<DividendEventData, CorporateDividendEvent> {

	public CorporateDividendEventSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<DividendEventData, CorporateDividendEvent> report) {
		return new CorporateDividendEventSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

	

}
