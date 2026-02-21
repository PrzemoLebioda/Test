package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class ExchangeQuoteWeeklyAdjustedSynchronizedDomainEventBuilder implements DomainEventBuilder<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjusted> {

	@Override
	public SubjectedPayload build(SynchronizationReport<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjusted> report) {
		return new ExchangeQuoteWeeklyAdjustedSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
