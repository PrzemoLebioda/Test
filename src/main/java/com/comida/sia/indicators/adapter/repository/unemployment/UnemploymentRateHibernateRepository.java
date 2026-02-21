package com.comida.sia.indicators.adapter.repository.unemployment;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.unemployment.UnemploymentRate;
import com.comida.sia.indicators.port.repository.unemployment.UnemploymentRateRepository;

import jakarta.persistence.EntityManager;

@Component("UnemploymentRateHibernateRepository")
public class UnemploymentRateHibernateRepository extends HibernateRepository implements UnemploymentRateRepository{

	public UnemploymentRateHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(UnemploymentRate unemploymentRate) {
		getEntityManager().persist(unemploymentRate);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(UnemploymentRate unemploymentRate) {
		getEntityManager().merge(unemploymentRate);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
