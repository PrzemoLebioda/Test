package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class ExchangeQuoteDailyAdjustedSynchronizedDomainEventBuilder implements DomainEventBuilder<ExchangeQuotationEntry, ExchangeQuoteDailyAdjusted> {

	@Override
	public SubjectedPayload build(SynchronizationReport<ExchangeQuotationEntry, ExchangeQuoteDailyAdjusted> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build quote daily adjusted synchronized domain event");
		
		return new ExchangeQuoteDailyAdjustedSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
