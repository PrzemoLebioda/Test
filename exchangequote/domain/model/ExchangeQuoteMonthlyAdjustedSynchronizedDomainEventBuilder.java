package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class ExchangeQuoteMonthlyAdjustedSynchronizedDomainEventBuilder implements DomainEventBuilder<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjusted> {

	@Override
	public SubjectedPayload build(SynchronizationReport<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjusted> report) {
		return new ExchangeQuoteMonthlyAdjustedSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
