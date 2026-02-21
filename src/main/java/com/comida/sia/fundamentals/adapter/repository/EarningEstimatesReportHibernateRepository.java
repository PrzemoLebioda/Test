package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.earnings.estimation.EarningEstimatesReport;
import com.comida.sia.fundamentals.port.repository.EarningEstimatesReportRepository;

import jakarta.persistence.EntityManager;

@Component("EarningEstimatesReportHibernateRepository")
public class EarningEstimatesReportHibernateRepository extends HibernateRepository implements EarningEstimatesReportRepository {

	public EarningEstimatesReportHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(EarningEstimatesReport earningEstimatesReport) {
		getEntityManager().persist(earningEstimatesReport);
		
		getEntityManager().flush();
		getEntityManager().clear();		
	}

	@Override
	public void update(EarningEstimatesReport earningEstimatesReport) {
		getEntityManager().merge(earningEstimatesReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
