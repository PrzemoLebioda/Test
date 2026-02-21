package com.comida.sia.indicators.domain.model.payroll;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

import lombok.Getter;

public class NonfarmPayrollMonthlySynchronizedDomainEvent implements SubjectedPayload {

	@Getter SynchronizationSummary summary;

	public NonfarmPayrollMonthlySynchronizedDomainEvent(SynchronizationSummary summary) {
		setSynchronizationSummary(summary);
	}
	
	private void setSynchronizationSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary must be provided in order to create nonfarm payroll synchronized domain event");
		this.summary = summary;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

	@Override
	public Subject getSubject() {
		return Subject.NONFARM_PAYROLL_RATE_MONTHLY_SYNCED;
	}

}
