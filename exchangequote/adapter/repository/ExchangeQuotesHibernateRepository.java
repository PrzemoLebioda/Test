package com.comida.sia.exchangequote.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.exchangequote.domain.model.ExchangeQuote;
import com.comida.sia.exchangequote.port.repository.ExchangeQuotesRepository;

import jakarta.persistence.EntityManager;

@Component("ExchangeQuotesHibernateRepository")
public class ExchangeQuotesHibernateRepository extends HibernateRepository implements ExchangeQuotesRepository{

	public ExchangeQuotesHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(ExchangeQuote exchangeQuote) {
		getEntityManager().persist(exchangeQuote);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(ExchangeQuote exchangeQuote) {
		getEntityManager().merge(exchangeQuote);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}
}
