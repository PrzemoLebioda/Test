package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class ExchangeQuoteInterday60MinSynchronizedDomainEventBuilder implements DomainEventBuilder<ExchangeQuotationEntry, ExchangeQuoteInterday60Min> {

	@Override
	public SubjectedPayload build(SynchronizationReport<ExchangeQuotationEntry, ExchangeQuoteInterday60Min> report) {
		return new ExchangeQuoteInterday60MinSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
