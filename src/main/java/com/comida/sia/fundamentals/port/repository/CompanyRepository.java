package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.company.Company;

public interface CompanyRepository {
	void store(Company company);
	void update(Company company);

	Company get(String tickerSymbol);
}
