package com.comida.sia.indicators.port.messaging.unemployment;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.unemployment.UnemploymentRateMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class UnemploymentRateMonthlySynchronizedNotification extends Notification<UnemploymentRateMonthlySynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1251319824937089040L;

	public UnemploymentRateMonthlySynchronizedNotification(UUID id,
			UnemploymentRateMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
