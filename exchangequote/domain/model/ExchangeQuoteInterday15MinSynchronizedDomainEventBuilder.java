package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class ExchangeQuoteInterday15MinSynchronizedDomainEventBuilder implements DomainEventBuilder<ExchangeQuotationEntry, ExchangeQuoteInterday15Min> {

	@Override
	public SubjectedPayload build(SynchronizationReport<ExchangeQuotationEntry, ExchangeQuoteInterday15Min> report) {
		return new ExchangeQuoteInterday15MinSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
