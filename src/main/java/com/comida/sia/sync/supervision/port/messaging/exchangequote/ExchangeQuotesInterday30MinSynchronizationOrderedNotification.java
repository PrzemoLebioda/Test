package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday30MinSynchronizationOrderedDomainEvent;

public class ExchangeQuotesInterday30MinSynchronizationOrderedNotification extends Notification<ExchangeQuotesInterday30MinSynchronizationOrderedDomainEvent> {

	private static final long serialVersionUID = -1301743163702713661L;

	public ExchangeQuotesInterday30MinSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesInterday30MinSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
