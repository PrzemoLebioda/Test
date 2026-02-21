package com.comida.sia.fundamentals.port.application;

import com.comida.sia.fundamentals.domain.model.company.Company;

public interface CompanyDataService {
	Company getCompany(String tickerSymbol);
}
