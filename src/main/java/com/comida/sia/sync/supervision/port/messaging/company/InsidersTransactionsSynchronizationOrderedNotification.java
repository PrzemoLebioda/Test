package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.InsidersTransactionsSynchronizationOrderedDomainEvent;

public class InsidersTransactionsSynchronizationOrderedNotification extends Notification<InsidersTransactionsSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7939569155646570586L;

	public InsidersTransactionsSynchronizationOrderedNotification(UUID id,
			InsidersTransactionsSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
