package com.comida.sia.exchangequote.port.messaging;

import java.util.UUID;

import com.comida.sia.exchangequote.domain.model.FirstQuotationSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class FirstQuotationSynchronizedNotification extends Notification<FirstQuotationSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4458148141341589889L;

	public FirstQuotationSynchronizedNotification(UUID id, FirstQuotationSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
