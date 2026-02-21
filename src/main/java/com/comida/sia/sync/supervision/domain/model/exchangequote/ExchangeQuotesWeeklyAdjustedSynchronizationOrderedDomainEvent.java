package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent extends SyncTickerTaggedDomainEvent{

	public ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNC_ORDERED;
	}

}
