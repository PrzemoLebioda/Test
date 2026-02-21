package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent;

public class ExchangeQuotesInterday01MinSynchronizationOrderedNotification extends Notification<ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent>{

	private static final long serialVersionUID = 1440569459956793719L;

	public ExchangeQuotesInterday01MinSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesInterday01MinSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
