package com.comida.sia.indicators.port.repository.unemployment;

import com.comida.sia.indicators.domain.model.unemployment.UnemploymentRate;

public interface UnemploymentRateRepository {
	public void store(UnemploymentRate unemploymentRate);
	public void update(UnemploymentRate unemploymentRate);
}
