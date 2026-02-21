package com.comida.sia.indicators.port.acquirer;

import java.io.IOException;

import com.comida.sia.indicators.domain.model.Interval;

public interface IndicatorsDataAcquirer {
	public IndicatorsData gatherIndicatorData(Interval interval) throws IOException;
}
