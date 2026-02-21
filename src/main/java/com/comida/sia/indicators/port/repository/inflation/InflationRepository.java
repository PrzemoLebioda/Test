package com.comida.sia.indicators.port.repository.inflation;

import com.comida.sia.indicators.domain.model.inflation.Inflation;

public interface InflationRepository {
	public void store(Inflation inflation);
	public void update(Inflation inflation);
}
