package com.comida.sia.sync.supervision.port.messaging.exchangequote;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent;

public class ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotification extends Notification<ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent>{

	private static final long serialVersionUID = 380712676436741144L;

	public ExchangeQuotesWeeklyAdjustedSynchronizationOrderedNotification(UUID id,
			ExchangeQuotesWeeklyAdjustedSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
