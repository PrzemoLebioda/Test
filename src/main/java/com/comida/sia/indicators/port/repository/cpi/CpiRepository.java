package com.comida.sia.indicators.port.repository.cpi;

import com.comida.sia.indicators.domain.model.cpi.ConsumerPriceIndex;

public interface CpiRepository {
	public void store(ConsumerPriceIndex cpi);
	public void update(ConsumerPriceIndex cpi);
}
