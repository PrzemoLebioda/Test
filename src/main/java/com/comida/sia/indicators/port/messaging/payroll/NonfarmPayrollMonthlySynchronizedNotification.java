package com.comida.sia.indicators.port.messaging.payroll;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.payroll.NonfarmPayrollMonthlySynchronizedDomainEvent;
import com.comida.sia.sharedkernel.messaging.Notification;

public class NonfarmPayrollMonthlySynchronizedNotification extends Notification<NonfarmPayrollMonthlySynchronizedDomainEvent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5838980574265927552L;

	public NonfarmPayrollMonthlySynchronizedNotification(UUID id,
			NonfarmPayrollMonthlySynchronizedDomainEvent payload) {
		super(id, payload);
	}

}
