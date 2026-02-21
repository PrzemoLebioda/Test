package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent extends SyncDomainEvent{

	public TreasuryYealdWeekly05YearSynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.TREASURY_YEALD_WEEKLY_05_YEAR_SYNC_ORDERED;
	}

}
