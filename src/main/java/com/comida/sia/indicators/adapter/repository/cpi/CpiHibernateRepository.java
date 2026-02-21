package com.comida.sia.indicators.adapter.repository.cpi;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.cpi.ConsumerPriceIndex;
import com.comida.sia.indicators.port.repository.cpi.CpiRepository;

import jakarta.persistence.EntityManager;

@Component("CpiHibernateRepository")
public class CpiHibernateRepository extends HibernateRepository implements CpiRepository {

	public CpiHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(ConsumerPriceIndex cpi) {
		getEntityManager().persist(cpi);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(ConsumerPriceIndex cpi) {
		getEntityManager().merge(cpi);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
