package com.comida.sia.indicators.port.repository.payroll;

import com.comida.sia.indicators.domain.model.payroll.NonfarmPayroll;

public interface NonfarmPayrollRepository {
	public void store(NonfarmPayroll nonfarmPayroll);
	public void update(NonfarmPayroll nonfarmPayroll);
}
