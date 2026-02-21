package com.comida.sia.indicators.port.repository.gdp;

import java.util.List;

import com.comida.sia.indicators.domain.model.gdp.GrossDomesticProduct;

public interface GdpRepository {
	void store(GrossDomesticProduct gdp);
	void update(GrossDomesticProduct gdp);
	
	List<GrossDomesticProduct> getFor();
}
