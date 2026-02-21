package com.comida.sia.sync.supervision.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.newsfeeds.NewsFeedSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.NewsFeedSynchronizationSypervisorRepository;

import jakarta.persistence.EntityManager;

@Component("NewsFeedSynchronizationSypervisorHibernateRepository")
public class NewsFeedSynchronizationSypervisorHibernateRepository extends HibernateRepository implements NewsFeedSynchronizationSypervisorRepository {

	public NewsFeedSynchronizationSypervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(NewsFeedSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(NewsFeedSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public NewsFeedSynchronizationSupervisor get() {
		NewsFeedSynchronizationSupervisor supervisor = null;
		String query = "FROM NewsFeedSynchronizationSupervisor";
		
		supervisor = (NewsFeedSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.getSingleResult();
		
		getEntityManager().flush();
		getEntityManager().clear();
		
		return supervisor;
	}

}
