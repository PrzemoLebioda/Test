package com.comida.sia.fundamentals.port.application.balancesheet;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface BalanceSheetsRegisterService {
	void synchronizeAnnualReports(String tickerSymbol, SynchronizationStateDto syncState);
	void synchronizeQuarterReports(String tickerSymbol, SynchronizationStateDto syncState);
}
