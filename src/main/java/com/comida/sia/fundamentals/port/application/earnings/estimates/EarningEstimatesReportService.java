package com.comida.sia.fundamentals.port.application.earnings.estimates;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface EarningEstimatesReportService {
	void synchronizeReports(String tickerSymbol, SynchronizationStateDto syncState);
}
