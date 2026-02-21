package com.comida.sia.indicators.port.messaging.durables;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.durables.DurableGoodsOrdersMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class DurableGoodsOrdersMonthlySynchronizedNotification extends Notification<DurableGoodsOrdersMonthlySynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1799298106466924203L;

	public DurableGoodsOrdersMonthlySynchronizedNotification(UUID id,
			DurableGoodsOrdersMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
