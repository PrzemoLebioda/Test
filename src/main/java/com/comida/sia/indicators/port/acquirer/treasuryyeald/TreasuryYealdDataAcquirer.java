package com.comida.sia.indicators.port.acquirer.treasuryyeald;

import java.io.IOException;

import com.comida.sia.indicators.domain.model.treasuryyeald.Interval;
import com.comida.sia.indicators.domain.model.treasuryyeald.Maturity;

public interface TreasuryYealdDataAcquirer {
	public TreasuryYealdData gatherGdpGeneral(Interval interval, Maturity maturity) throws IOException;
}
