package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent;

public class ExchangeQuotesDailyAdjustedSynchronizationOrderedNotification extends Notification<ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent>{

	private static final long serialVersionUID = 6435613779018466995L;

	public ExchangeQuotesDailyAdjustedSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesDailyAdjustedSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
