package com.comida.sia.sync.supervision.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.indicators.IndicatorsSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.IndicatorsSynchronizationSypervisorRepository;

import jakarta.persistence.EntityManager;

@Component("IndicatorsSynchronizationSypervisorHibernateRepository")
public class IndicatorsSynchronizationSypervisorHibernateRepository extends HibernateRepository implements  IndicatorsSynchronizationSypervisorRepository {

	public IndicatorsSynchronizationSypervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(IndicatorsSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();	
	}

	@Override
	public void update(IndicatorsSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public IndicatorsSynchronizationSupervisor get() {
		IndicatorsSynchronizationSupervisor supervisor = null;
		String query = "FROM IndicatorsSynchronizationSupervisor";
		
		supervisor = (IndicatorsSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.getSingleResult();
		
		getEntityManager().flush();
		getEntityManager().clear();
		
		return supervisor;
	}

}
