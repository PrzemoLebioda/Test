package com.comida.sia.indicators.port.repository.intrestrate;

import com.comida.sia.indicators.domain.model.intrestrate.IntrestRate;

public interface IntrestRateRepository {
	public void store(IntrestRate interstRate);
	public void update(IntrestRate interstRate);
}
