package com.comida.sia.indicators.domain.model.payroll;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class NonfarmPayrollMonthlySynchronizedDomainEventBuilder implements DomainEventBuilder<IndicatorsDataEntry, NonfarmPayroll> {

	@Override
	public SubjectedPayload build(SynchronizationReport<IndicatorsDataEntry, NonfarmPayroll> report) {
		AssertionConcern.assertNotNull(report, "Synchronization report is mandatory in order do build nonfarm payroll synchronized domain event");
		
		return new NonfarmPayrollMonthlySynchronizedDomainEvent(report.getSummary());
	}

}
