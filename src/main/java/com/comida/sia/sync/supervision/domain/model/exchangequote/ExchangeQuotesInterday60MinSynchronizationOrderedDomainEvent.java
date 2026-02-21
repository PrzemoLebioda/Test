package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent extends SyncTickerTaggedDomainEvent{

	public ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_INTERDAY_60_SYNC_ORDERED;
	}

}
