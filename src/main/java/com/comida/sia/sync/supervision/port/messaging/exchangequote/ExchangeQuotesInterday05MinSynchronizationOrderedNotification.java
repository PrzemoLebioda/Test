package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent;

public class ExchangeQuotesInterday05MinSynchronizationOrderedNotification extends Notification<ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent> {

	private static final long serialVersionUID = -7152916431769643467L;

	public ExchangeQuotesInterday05MinSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesInterday05MinSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
