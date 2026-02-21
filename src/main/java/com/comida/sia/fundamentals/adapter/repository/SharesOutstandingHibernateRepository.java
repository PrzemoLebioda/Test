package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.sharesoutstanding.SharesOutstandingReport;
import com.comida.sia.fundamentals.port.repository.SharesOutstandingRepository;

import jakarta.persistence.EntityManager;

@Component("SharesOutstandingHibernateRepository")
public class SharesOutstandingHibernateRepository extends HibernateRepository implements SharesOutstandingRepository{

	public SharesOutstandingHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(SharesOutstandingReport sharesOutstandingReport) {
		getEntityManager().persist(sharesOutstandingReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(SharesOutstandingReport sharesOutstandingReport) {
		getEntityManager().merge(sharesOutstandingReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
