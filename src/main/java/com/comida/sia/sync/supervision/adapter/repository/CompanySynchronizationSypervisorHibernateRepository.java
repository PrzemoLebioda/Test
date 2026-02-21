package com.comida.sia.sync.supervision.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.company.CompanySynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.CompanySynchronizationSypervisorRepository;

import jakarta.persistence.EntityManager;

@Component("CompanySynchronizationSypervisorHibernateRepository")
public class CompanySynchronizationSypervisorHibernateRepository extends HibernateRepository implements CompanySynchronizationSypervisorRepository{

	public CompanySynchronizationSypervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CompanySynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();		
	}

	@Override
	public void update(CompanySynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public CompanySynchronizationSupervisor get(String tickerSymbol) {
		CompanySynchronizationSupervisor supervisor;
		
		String query = "FROM CompanySynchronizationSupervisor "
			 	 + "WHERE tickerSymbol = :tickerSymbol";
		
		supervisor = (CompanySynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		//getEntityManager().flush();
		//getEntityManager().clear();
		
		return supervisor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanySynchronizationSupervisor> getAll() {
		List<CompanySynchronizationSupervisor> supervisorList;
		
		String query = "FROM CompanySynchronizationSupervisor";
		
		supervisorList = getEntityManager()
				.createQuery(query)
				.getResultList();
		
		getEntityManager().flush();
		getEntityManager().clear();
		
		return supervisorList;
	}

}
