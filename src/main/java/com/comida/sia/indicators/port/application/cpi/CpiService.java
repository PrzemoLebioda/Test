package com.comida.sia.indicators.port.application.cpi;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CpiService {
	public void synchronizeSemiannualCpi(SynchronizationStateDto syncState);
	public void synchronizeMonthlyCpi(SynchronizationStateDto syncState);
}
