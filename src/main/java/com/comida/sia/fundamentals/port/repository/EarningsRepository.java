package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.earnings.EarningReport;

public interface EarningsRepository {
	void store(EarningReport earningReport);
	void update(EarningReport earningReport);
}
