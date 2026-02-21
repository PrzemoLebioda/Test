package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.domain.SyncTickerTaggedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent extends SyncTickerTaggedDomainEvent{

	public ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent(String tickerSymbol, SynchronizationStateDto syncState) {
		super(tickerSymbol, syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNC_ORDERED;
	}

}
