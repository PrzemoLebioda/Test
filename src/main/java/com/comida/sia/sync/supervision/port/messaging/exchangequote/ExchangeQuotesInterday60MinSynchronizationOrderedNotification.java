package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent;

public class ExchangeQuotesInterday60MinSynchronizationOrderedNotification extends Notification<ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent> {

	private static final long serialVersionUID = 7290227676986989838L;

	public ExchangeQuotesInterday60MinSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesInterday60MinSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
