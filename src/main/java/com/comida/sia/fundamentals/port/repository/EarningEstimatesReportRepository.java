package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReport;

public interface EarningEstimatesReportRepository {
	void store(EarningEstimatesReport earningEstimatesReport);
	void update(EarningEstimatesReport earningEstimatesReport);
}
