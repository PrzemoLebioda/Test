package com.comida.sia.fundamentals.port.application.cashflow;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CashFlowsRegisterService {
	void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState);
}
