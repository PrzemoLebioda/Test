package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningEventData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class EarningsCalendarSynchronizedDomainEventBuilder implements DomainEventBuilder<EarningEventData, CorporateEarningEvent>{

	@Override
	public SubjectedPayload build(SynchronizationReport<EarningEventData, CorporateEarningEvent> report) {
		return new EarningsCalendarSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

	

}
