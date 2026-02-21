package com.comida.sia.sync.supervision.port.messaging.company;

import java.util.UUID;

import com.comida.sia.sharedkernel.messaging.Notification;
import com.comida.sia.sync.supervision.domain.model.company.events.CompanyKeyMetricsSyncOrderedDomainEvent;

public class CompanyKeyMetricsSyncOrderedNotification extends Notification<CompanyKeyMetricsSyncOrderedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4596767747232804038L;

	public CompanyKeyMetricsSyncOrderedNotification(UUID id, CompanyKeyMetricsSyncOrderedDomainEvent payload) {
		super(id, payload);
	}

}
