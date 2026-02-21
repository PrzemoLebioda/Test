package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.cashflow.CashFlowReport;
import com.comida.sia.fundamentals.port.repository.CashFlowRepository;

import jakarta.persistence.EntityManager;

@Component("CashFlowHibernateRepository")
public class CashFlowHibernateRepository extends HibernateRepository implements CashFlowRepository {

	public CashFlowHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CashFlowReport report) {
		getEntityManager().persist(report);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(CashFlowReport report) {
		getEntityManager().merge(report);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

}
