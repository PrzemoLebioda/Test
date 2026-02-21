package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.OptionsSyncOrderedDomainEvent;

public class OptionsSyncOrderedNotification extends Notification<OptionsSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2326740181881425433L;

	public OptionsSyncOrderedNotification(UUID id, OptionsSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
