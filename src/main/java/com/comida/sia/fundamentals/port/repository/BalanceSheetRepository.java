package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetReport;

public interface BalanceSheetRepository {
	void store(BalanceSheetReport report);
}
