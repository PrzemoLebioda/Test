package com.comida.sia.sharedkernel.domain;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.TaggedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public abstract class SyncTickerTaggedDomainEvent extends SyncDomainEvent implements TaggedPayload {
	
	private String tickerSymbol;
	
	public SyncTickerTaggedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(syncState);
		setTickerSymbol(tickerSymbol);
	}

	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create " + getClassName());
		this.tickerSymbol = tickerSymbol;
	}
	
	@Override
	public String getTag() {
		return tickerSymbol;
	}
}
