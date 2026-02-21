package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.income.IncomeStatementReport;

public interface IncomeStatementRepository {
	void store(IncomeStatementReport incomeStatementReport);
	void update(IncomeStatementReport incomeStatementReport);
}
