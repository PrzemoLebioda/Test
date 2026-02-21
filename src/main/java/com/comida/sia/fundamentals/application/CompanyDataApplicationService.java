package com.comida.sia.fundamentals.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.port.application.CompanyDataService;
import com.comida.sia.fundamentals.port.repository.CompanyRepository;

@Component("CompanyDataApplicationService")
public class CompanyDataApplicationService implements CompanyDataService{

	@Autowired
	@Qualifier("CompanyHibernateRepository")
	private CompanyRepository repository;
	
	@Override
	public Company getCompany(String tickerSymbol) {
		return repository.get(tickerSymbol);
	}

}
