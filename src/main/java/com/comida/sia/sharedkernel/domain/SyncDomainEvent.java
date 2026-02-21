package com.comida.sia.sharedkernel.domain;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

import lombok.Getter;

public abstract class SyncDomainEvent implements SubjectedPayload{
	@Getter protected SynchronizationStateDto syncState;
	
	public SyncDomainEvent(SynchronizationStateDto syncState) {
		setSyncState(syncState);
	}
	
	private void setSyncState(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, "Synchronization state for stok synchronization order event is mandatory");
		this.syncState = syncState;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}
}