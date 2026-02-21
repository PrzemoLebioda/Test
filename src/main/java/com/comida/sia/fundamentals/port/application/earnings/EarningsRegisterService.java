package com.comida.sia.fundamentals.port.application.earnings;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface EarningsRegisterService {
	void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState);
}
