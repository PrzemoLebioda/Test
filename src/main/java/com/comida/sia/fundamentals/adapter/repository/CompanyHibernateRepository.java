package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.company.Company;
import com.comida.sia.fundamentals.port.repository.CompanyRepository;

import jakarta.persistence.EntityManager;

@Component("CompanyHibernateRepository")
public class CompanyHibernateRepository extends HibernateRepository implements CompanyRepository{

	public CompanyHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(Company company) {
		getEntityManager().persist(company);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(Company company) {
		getEntityManager().merge(company);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public Company get(String tickerSymbol) {
		Company company;
		
		String query = "FROM Company "
				 	 + "WHERE tickerSymbol = :tickerSymbol";
	
		company = (Company)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		getEntityManager().detach(company);
	
		return company;
	}	

}
