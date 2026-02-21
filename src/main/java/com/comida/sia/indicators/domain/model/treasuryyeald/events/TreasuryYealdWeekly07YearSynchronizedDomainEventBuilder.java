package com.comida.sia.indicators.domain.model.treasuryyeald.events;

import com.comida.sia.indicators.domain.model.treasuryyeald.TreasuryYeald;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class TreasuryYealdWeekly07YearSynchronizedDomainEventBuilder implements DomainEventBuilder<TreasuryYealdEntry, TreasuryYeald> {

	public TreasuryYealdWeekly07YearSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<TreasuryYealdEntry, TreasuryYeald> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build gdp synchronized domain event");
		return new TreasuryYealdWeekly07YearSynchronizedDomainEvent(report.getSummary());
	}

}
