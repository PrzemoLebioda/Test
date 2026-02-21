package com.comida.sia.indicators.port.application.retailsales;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface RetailSalesService {
	public void synchronizeMonthly(SynchronizationStateDto syncState);
}
