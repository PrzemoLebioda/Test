package com.comida.sia.indicators.domain.model.inflation;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

import lombok.Getter;

public class InflationAnnualSynchronizedDomainEvent implements SubjectedPayload {

	@Getter SynchronizationSummary summary;

	public InflationAnnualSynchronizedDomainEvent(SynchronizationSummary summary) {
		setSynchronizationSummary(summary);
	}
	
	private void setSynchronizationSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary must be provided in order to create inflation synchronized domain event");
		this.summary = summary;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

	@Override
	public Subject getSubject() {
		return Subject.INFLATION_ANNUAL_SYNCED;
	}

}
