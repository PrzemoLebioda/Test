package com.comida.sia.sync.supervision.domain.model;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;

public abstract class SyncDomainEventAnnouncer implements DomainEventAnnouncer{
	protected SynchronizationState syncState;
	protected Notifier notifier;
	
	public SyncDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState) {
		this.setSyncState(syncState);
		this.setNotifier(notifier);
	}
	
	private void setSyncState(SynchronizationState syncState) {
		AssertionConcern.assertNotNull(syncState, "Sync state is mandatory in order to create domain event");
		this.syncState = syncState;
	}
	
	private void setNotifier(Notifier notifier) {
		AssertionConcern.assertNotNull(syncState, "Notifier is mandatory in order to create domain event");
		this.notifier = notifier;
	}
}
