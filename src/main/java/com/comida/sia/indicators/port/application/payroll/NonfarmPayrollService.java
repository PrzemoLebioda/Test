package com.comida.sia.indicators.port.application.payroll;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface NonfarmPayrollService {
	public void synchronizeMonthly(SynchronizationStateDto syncState);
}
