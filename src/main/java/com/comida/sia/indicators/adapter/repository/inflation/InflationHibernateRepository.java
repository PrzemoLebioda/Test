package com.comida.sia.indicators.adapter.repository.inflation;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.inflation.Inflation;
import com.comida.sia.indicators.port.repository.inflation.InflationRepository;

import jakarta.persistence.EntityManager;

@Component("InflationHibernateRepository")
public class InflationHibernateRepository extends HibernateRepository implements InflationRepository{

	public InflationHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(Inflation inflation) {
		getEntityManager().persist(inflation);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(Inflation inflation) {
		getEntityManager().merge(inflation);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
