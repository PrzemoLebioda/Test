package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.corpoevents.splits.CorporateSplitEvent;
import com.comida.sia.fundamentals.port.repository.CorporateSplitEventRepository;

import jakarta.persistence.EntityManager;

@Component("CorporateSplitEventHibernateRepository")
public class CorporateSplitEventHibernateRepository extends HibernateRepository implements CorporateSplitEventRepository {

	public CorporateSplitEventHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CorporateSplitEvent corporateSplitEvent) {
		getEntityManager().persist(corporateSplitEvent);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(CorporateSplitEvent corporateSplitEvent) {
		getEntityManager().merge(corporateSplitEvent);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
