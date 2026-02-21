package com.comida.sia.sync.supervision.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.exchangequote.InterdayExchangeQuoteSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.InterdayExchangeQuoteSynchronizationSupervisorRepository;

import jakarta.persistence.EntityManager;

@Component("InterdayExchangeQuoteSynchronizationSupervisorHibernateRepository")
public class InterdayExchangeQuoteSynchronizationSupervisorHibernateRepository extends HibernateRepository implements InterdayExchangeQuoteSynchronizationSupervisorRepository{

	public InterdayExchangeQuoteSynchronizationSupervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(InterdayExchangeQuoteSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(InterdayExchangeQuoteSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public InterdayExchangeQuoteSynchronizationSupervisor get(String tickerSymbol) {
		InterdayExchangeQuoteSynchronizationSupervisor supervisor = null;
		String query = "FROM InterdayExchangeQuoteSynchronizationSupervisor"
					+ " WHERE tickerSymbol = :tickerSymbol";
		
		supervisor = (InterdayExchangeQuoteSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		//getEntityManager().flush();
		//getEntityManager().clear();
		
		return supervisor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InterdayExchangeQuoteSynchronizationSupervisor> getAll() {
		List<InterdayExchangeQuoteSynchronizationSupervisor> supervisorList;
		
		String query = "FROM InterdayExchangeQuoteSynchronizationSupervisor";
		
		supervisorList = getEntityManager()
				.createQuery(query)
				.getResultList();
		
		//getEntityManager().flush();
		//getEntityManager().clear();
		
		return supervisorList;
	}

}
