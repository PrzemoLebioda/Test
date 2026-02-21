package com.comida.sia.options.port.repository;

import java.util.List;

import com.comida.sia.options.domain.model.Option;

public interface OptionRepository {
	void store(Option option);
	void update(Option option);
	
	List<Option> getFor(String tickerSybmol);
}
