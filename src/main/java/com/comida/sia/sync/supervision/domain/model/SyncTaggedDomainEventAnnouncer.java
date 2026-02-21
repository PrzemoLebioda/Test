package com.comida.sia.sync.supervision.domain.model;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;

public abstract class SyncTaggedDomainEventAnnouncer extends SyncDomainEventAnnouncer {
	
	protected String tag;

	public SyncTaggedDomainEventAnnouncer(Notifier notifier, SynchronizationState syncState, String tag) {
		super(notifier, syncState);
		setTag(tag);
	}
	
	private void setTag(String tag) {
		AssertionConcern.assertNotEmpty(tag, "Tag is mandatory in order to create sync tagged domain event announcer");
		this.tag = tag;
	}

}
