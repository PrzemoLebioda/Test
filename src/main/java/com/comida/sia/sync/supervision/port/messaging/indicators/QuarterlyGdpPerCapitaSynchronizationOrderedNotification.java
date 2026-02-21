package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent;

public class QuarterlyGdpPerCapitaSynchronizationOrderedNotification extends Notification<QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647229833752644546L;

	public QuarterlyGdpPerCapitaSynchronizationOrderedNotification(UUID id,
			QuarterlyGdpPerCapitaSynchronizationOrderedDomainEvent payload) {
		super(id, payload);
	}

}
