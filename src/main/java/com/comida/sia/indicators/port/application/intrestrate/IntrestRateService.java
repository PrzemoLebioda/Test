package com.comida.sia.indicators.port.application.intrestrate;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface IntrestRateService {
	public void synchronizeMonthlyIntrestRate(SynchronizationStateDto syncState);
	public void synchronizeWeeklyIntrestRate(SynchronizationStateDto syncState);
	public void synchronizeDailyIntrestRate(SynchronizationStateDto syncState);
}
