package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesInterday15MinSynchronizationOrderedDomainEvent;

public class ExchangeQuotesInterday15MinSynchronizationOrderedNotification extends Notification<ExchangeQuotesInterday15MinSynchronizationOrderedDomainEvent> {

	private static final long serialVersionUID = 8266893765068728195L;

	public ExchangeQuotesInterday15MinSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesInterday15MinSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
