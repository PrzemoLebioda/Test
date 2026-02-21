package com.comida.sia.fundamentals.port.application;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CompanyService {
	void synchronize(String tickerSymbol, SynchronizationStateDto syncState);
}
