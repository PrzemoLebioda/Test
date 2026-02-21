package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.earnings.EarningReport;
import com.comida.sia.fundamentals.port.repository.EarningsRepository;

import jakarta.persistence.EntityManager;

@Component("EarningsHibernateRepository")
public class EarningsHibernateRepository extends HibernateRepository implements EarningsRepository {

	public EarningsHibernateRepository(EntityManager entityManager) {
		super(entityManager);

	}

	@Override
	public void store(EarningReport earningReport) {
		getEntityManager().persist(earningReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(EarningReport earningReport) {
		getEntityManager().merge(earningReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}
}
