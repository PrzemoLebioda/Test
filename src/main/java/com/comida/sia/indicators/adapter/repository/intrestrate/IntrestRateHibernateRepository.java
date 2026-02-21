package com.comida.sia.indicators.adapter.repository.intrestrate;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.intrestrate.IntrestRate;
import com.comida.sia.indicators.port.repository.intrestrate.IntrestRateRepository;

import jakarta.persistence.EntityManager;

@Component("IntrestRateHibernateRepository")
public class IntrestRateHibernateRepository extends HibernateRepository implements IntrestRateRepository {

	public IntrestRateHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(IntrestRate interstRate) {
		getEntityManager().persist(interstRate);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(IntrestRate interstRate) {
		getEntityManager().merge(interstRate);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
