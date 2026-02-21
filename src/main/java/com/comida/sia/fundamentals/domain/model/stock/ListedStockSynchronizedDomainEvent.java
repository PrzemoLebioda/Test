package com.comida.sia.fundamentals.domain.model.stock;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

import lombok.Getter;

public class ListedStockSynchronizedDomainEvent implements SubjectedPayload {

	@Getter SynchronizationSummary summary;
	
	public ListedStockSynchronizedDomainEvent(SynchronizationSummary summary) {
		setSynchronizationSummary(summary);
	}
	
	private void setSynchronizationSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary must be provided in order to create listed stock data synchronized domain event");
		this.summary = summary;
	}

	@Override
	public Subject getSubject() {
		return Subject.LISTED_STOCK_SYNCED;
	}

	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

}
