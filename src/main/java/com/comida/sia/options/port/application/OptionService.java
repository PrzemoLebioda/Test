package com.comida.sia.options.port.application;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface OptionService {
	void registerOption(String tickerSymbol, SynchronizationStateDto syncState);
}
