package com.comida.sia.indicators.port.application.inflation;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface InflationService {
	public void synchronizeAnnualInflation(SynchronizationStateDto syncState);
}
