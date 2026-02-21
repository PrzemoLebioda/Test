package com.comida.sia.sync.supervision.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.stock.StockSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.StockSynchronizationSypervisorRepository;

import jakarta.persistence.EntityManager;

@Component("StockSynchronizationSypervisorHibernateRepository")
public class StockSynchronizationSypervisorHibernateRepository extends HibernateRepository implements StockSynchronizationSypervisorRepository{

	public StockSynchronizationSypervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(StockSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();	
	}

	@Override
	public void update(StockSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public StockSynchronizationSupervisor get() {
		StockSynchronizationSupervisor supervisor = null;
		String query = "FROM StockSynchronizationSupervisor";
		
		supervisor = (StockSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.getSingleResult();
		
		getEntityManager().flush();
		getEntityManager().clear();
		
		return supervisor;
	}

}
