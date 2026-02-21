package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class ExchangeQuoteSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_MONTHLY;
			case EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNCED: 
				return SynchronizationScope.EXCHANGE_QUOTE_MONTHLY;
		
			case EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_WEEKLY;
			case EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_WEEKLY;
		
			case EXCHANGE_QUOTE_DAILY_ADJUSTED_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_DAILY;
			case EXCHANGE_QUOTE_DAILY_ADJUSTED_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_DAILY;
						
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
