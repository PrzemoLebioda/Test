package com.comida.sia.sync.supervision.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.exchangequote.ExchangeQuoteSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.ExchangeQuoteSynchronizationSupervisorRepository;

import jakarta.persistence.EntityManager;

@Component("ExchangeQuoteSynchronizationSupervisorHibernateRepository")
public class ExchangeQuoteSynchronizationSupervisorHibernateRepository extends HibernateRepository implements ExchangeQuoteSynchronizationSupervisorRepository {

	public ExchangeQuoteSynchronizationSupervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(ExchangeQuoteSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(ExchangeQuoteSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public ExchangeQuoteSynchronizationSupervisor get(String tickerSymbol) {
		ExchangeQuoteSynchronizationSupervisor supervisor = null;
		String query = "FROM ExchangeQuoteSynchronizationSupervisor"
					+ " WHERE tickerSymbol = :tickerSymbol";
		
		supervisor = (ExchangeQuoteSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		//getEntityManager().flush();
		//getEntityManager().clear();
		
		return supervisor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExchangeQuoteSynchronizationSupervisor> getAll() {
		List<ExchangeQuoteSynchronizationSupervisor> supervisorList;
		
		String query = "FROM ExchangeQuoteSynchronizationSupervisor";
		
		supervisorList = getEntityManager()
				.createQuery(query)
				.getResultList();
		
		//getEntityManager().flush();
		//getEntityManager().clear();
		
		return supervisorList;
	}

}
