package com.comida.sia.fundamentals.port.messaging.company;

import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.company.CompanyKeyMetricsSynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class CompanyKeyMetricsSynchronizedNotification extends Notification<CompanyKeyMetricsSynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8039117256641941489L;

	public CompanyKeyMetricsSynchronizedNotification(UUID id, CompanyKeyMetricsSynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
