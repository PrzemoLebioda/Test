package com.comida.sia.indicators.port.application.durables;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface DurableGoodsOrdersService {
	public void synchronizeMonthly(SynchronizationStateDto syncState);
}
