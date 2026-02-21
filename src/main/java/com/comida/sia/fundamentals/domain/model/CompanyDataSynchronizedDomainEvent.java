package com.comida.sia.fundamentals.domain.model;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

import lombok.Getter;

public abstract class CompanyDataSynchronizedDomainEvent implements SubjectedPayload {
	@Getter String tickerSymbol;
	@Getter SynchronizationSummary summary;
	
	public CompanyDataSynchronizedDomainEvent(
			String tickerSymbol,
			SynchronizationSummary summary) {
		
		setTickerSymbol(tickerSymbol);
		setSynchronizationSummary(summary);
		
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol must be provided in order to create the company data synchronized domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setSynchronizationSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary must be provided in order to create company data synchronized domain event");
		this.summary = summary;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}
}
