package com.comida.sia.fundamentals.port.application.income;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface IncomeStatementsRegisterService {
	void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState);
}
