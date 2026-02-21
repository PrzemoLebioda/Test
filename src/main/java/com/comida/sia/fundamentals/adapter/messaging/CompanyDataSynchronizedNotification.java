package com.comida.sia.fundamentals.adapter.messaging;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.CompanyDataSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CompanyDataSynchronizedNotification extends Notification<CompanyDataSynchronizedDomainEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4181856170567073087L;

	public CompanyDataSynchronizedNotification(UUID id, CompanyDataSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
