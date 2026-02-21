package com.comida.sia.sync.supervision.port.messaging.indicators;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.indicators.events.RetailSalesMonthlySynchronizationOrderedDomainEvent;

public class RetailSalesMonthlySynchronizationOrderedNotidication extends Notification<RetailSalesMonthlySynchronizationOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8888474750149422214L;

	public RetailSalesMonthlySynchronizationOrderedNotidication(UUID id,
			RetailSalesMonthlySynchronizationOrderedDomainEvent payload) {
		super(id, payload);
		// TODO Auto-generated constructor stub
	}

}
