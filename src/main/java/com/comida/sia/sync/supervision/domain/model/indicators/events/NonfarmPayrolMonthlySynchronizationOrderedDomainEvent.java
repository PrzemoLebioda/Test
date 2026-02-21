package com.comida.sia.sync.supervision.domain.model.indicators.events;

import com.comida.sia.sharedkernel.domain.SyncDomainEvent;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class NonfarmPayrolMonthlySynchronizationOrderedDomainEvent extends SyncDomainEvent {

	public NonfarmPayrolMonthlySynchronizationOrderedDomainEvent(SynchronizationStateDto syncState) {
		super(syncState);
	}

	@Override
	public Subject getSubject() {
		return Subject.NONFARM_PAYROLL_MONTHLY_SYNC_ORDERED;
	}

}
