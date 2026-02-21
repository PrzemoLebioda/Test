package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.corpoevents.dividend.CorporateDividendEvent;
import com.comida.sia.fundamentals.port.repository.CorporateDividendEventRepository;

import jakarta.persistence.EntityManager;

@Component("CorporateDividendEventHibernateRepository")
public class CorporateDividendEventHibernateRepository extends HibernateRepository implements CorporateDividendEventRepository{

	public CorporateDividendEventHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CorporateDividendEvent corporateDividendEvent) {
		getEntityManager().persist(corporateDividendEvent);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(CorporateDividendEvent corporateDividendEvent) {
		getEntityManager().merge(corporateDividendEvent);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	

}
