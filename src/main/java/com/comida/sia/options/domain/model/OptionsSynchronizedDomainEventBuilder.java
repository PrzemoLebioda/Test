package com.comida.sia.options.domain.model;

import com.comida.sia.options.port.acquirer.OptionData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class OptionsSynchronizedDomainEventBuilder implements DomainEventBuilder<OptionData, Option>{

	public OptionsSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<OptionData, Option> report) {
		return new OptionsSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
