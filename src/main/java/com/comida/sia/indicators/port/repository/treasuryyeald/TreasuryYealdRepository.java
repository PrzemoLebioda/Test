package com.comida.sia.indicators.port.repository.treasuryyeald;

import com.comida.sia.indicators.domain.model.treasuryyeald.TreasuryYeald;

public interface TreasuryYealdRepository {
	void store(TreasuryYeald treasuryYeald);
	void update(TreasuryYeald treasuryYeald);
	
	//List<GrossDomesticProduct> getFor();
}
