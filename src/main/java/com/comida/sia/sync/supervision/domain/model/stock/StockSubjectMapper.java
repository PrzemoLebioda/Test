package com.comida.sia.sync.supervision.domain.model.stock;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class StockSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case LISTED_STOCKS_SYNC_ORDERED: 
				return SynchronizationScope.LISTING_SYNC;
			case LISTED_STOCK_SYNCED:
				return SynchronizationScope.LISTING_SYNC;
			case DELISTED_STOCKS_SYNC_ORDERED:
				return SynchronizationScope.DELISTING_SYNC;
			case DELISTED_STOCK_SYNCED:
				return SynchronizationScope.DELISTING_SYNC;
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
