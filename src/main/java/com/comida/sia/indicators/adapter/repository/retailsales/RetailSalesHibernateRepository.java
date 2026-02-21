package com.comida.sia.indicators.adapter.repository.retailsales;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.retailsales.RetailSales;
import com.comida.sia.indicators.port.repository.retailsales.RetailSalesRepository;

import jakarta.persistence.EntityManager;

@Component("RetailSalesHibernateRepository")
public class RetailSalesHibernateRepository extends HibernateRepository implements RetailSalesRepository{

	public RetailSalesHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(RetailSales retailSales) {
		getEntityManager().persist(retailSales);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(RetailSales retailSales) {
		getEntityManager().merge(retailSales);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
