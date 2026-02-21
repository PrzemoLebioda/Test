package com.comida.sia.indicators.port.repository.retailsales;

import com.comida.sia.indicators.domain.model.retailsales.RetailSales;

public interface RetailSalesRepository {
	public void store(RetailSales retailSales);
	public void update(RetailSales retailSales);
}
