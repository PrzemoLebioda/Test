package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent;

public class ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotification extends Notification<ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent> {

	private static final long serialVersionUID = -8049270581058011630L;

	public ExchangeQuotesMonthlyAdjustedSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesMonthlyAdjustedSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
