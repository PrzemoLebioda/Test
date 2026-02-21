package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowReport;

public interface CashFlowRepository {
	void store(CashFlowReport report);
	void update(CashFlowReport report);

}
