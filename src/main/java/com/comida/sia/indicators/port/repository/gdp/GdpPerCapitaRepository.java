package com.comida.sia.indicators.port.repository.gdp;

import java.util.List;

import com.comida.sia.indicators.domain.model.gdp.GrossDomesticProductPerCapita;

public interface GdpPerCapitaRepository {
	void store(GrossDomesticProductPerCapita gdp);
	void update(GrossDomesticProductPerCapita gdp);
	
	List<GrossDomesticProductPerCapita> getFor();
}
