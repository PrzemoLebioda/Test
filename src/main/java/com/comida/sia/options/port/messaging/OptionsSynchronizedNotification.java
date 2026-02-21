package com.comida.sia.options.port.messaging;

import java.util.UUID;

import com.comida.sia.options.domain.model.OptionsSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class OptionsSynchronizedNotification extends Notification<OptionsSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9202448731090311316L;

	public OptionsSynchronizedNotification(UUID id, OptionsSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
