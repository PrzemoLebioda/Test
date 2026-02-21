package com.comida.sia.indicators.port.application.unemployment;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface UnemploymentRateService {
	public void synchronizeMonthly(SynchronizationStateDto syncState);
}
