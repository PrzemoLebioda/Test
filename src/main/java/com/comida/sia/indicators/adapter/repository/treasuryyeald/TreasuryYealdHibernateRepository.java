package com.comida.sia.indicators.adapter.repository.treasuryyeald;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.treasuryyeald.TreasuryYeald;
import com.comida.sia.indicators.port.repository.treasuryyeald.TreasuryYealdRepository;

import jakarta.persistence.EntityManager;

@Component("TreasuryYealdHibernateRepository")
public class TreasuryYealdHibernateRepository extends HibernateRepository implements TreasuryYealdRepository{

	public TreasuryYealdHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(TreasuryYeald treasuryYeald) {
		getEntityManager().persist(treasuryYeald);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(TreasuryYeald treasuryYeald) {
		getEntityManager().merge(treasuryYeald);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
