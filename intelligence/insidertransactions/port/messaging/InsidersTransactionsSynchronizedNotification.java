package com.comida.sia.intelligence.insidertransactions.port.messaging;

import java.util.UUID;

import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransactionsSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class InsidersTransactionsSynchronizedNotification extends Notification<InsiderTransactionsSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7356443760259427335L;

	public InsidersTransactionsSynchronizedNotification(UUID id, InsiderTransactionsSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
