package com.comida.sia.sync.supervision.domain.model.exchangequote;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class InterdayExchangeQuoteSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case EXCHANGE_QUOTE_INTERDAY_60_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_60_MIN;
			case EXCHANGE_QUOTE_INTERDAY_60_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_60_MIN;
	
			case EXCHANGE_QUOTE_INTERDAY_30_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_30_MIN;
			case EXCHANGE_QUOTE_INTERDAY_30_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_30_MIN;
	
			case EXCHANGE_QUOTE_INTERDAY_15_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_15_MIN;
			case EXCHANGE_QUOTE_INTERDAY_15_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_15_MIN;
	
			case EXCHANGE_QUOTE_INTERDAY_05_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_05_MIN;
			case EXCHANGE_QUOTE_INTERDAY_05_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_05_MIN;
	
			case EXCHANGE_QUOTE_INTERDAY_01_SYNC_ORDERED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_01_MIN;
			case EXCHANGE_QUOTE_INTERDAY_01_SYNCED:
				return SynchronizationScope.EXCHANGE_QUOTE_INTERDAY_01_MIN;
				
			case FIRST_QUITATION_SYNCED:
				return SynchronizationScope.FIRST_QUITATION;
			
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
