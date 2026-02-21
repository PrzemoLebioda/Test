package com.comida.sia.indicators.port.application.gdp;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface GdpService {
	public void synchronizeAnnualGdp(SynchronizationStateDto syncState);
	public void synchronizeQuarterlyGdp(SynchronizationStateDto syncState);
}
