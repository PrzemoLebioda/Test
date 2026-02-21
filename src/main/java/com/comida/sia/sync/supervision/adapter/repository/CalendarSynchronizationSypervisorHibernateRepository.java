package com.comida.sia.sync.supervision.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.sync.supervision.domain.model.calendar.CalendarSynchronizationSupervisor;
import com.comida.sia.sync.supervision.port.repository.CalendarSynchronizationSypervisorRepository;

import jakarta.persistence.EntityManager;

@Component("CalendarSynchronizationSypervisorHibernateRepository")
public class CalendarSynchronizationSypervisorHibernateRepository extends HibernateRepository implements CalendarSynchronizationSypervisorRepository {

	public CalendarSynchronizationSypervisorHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CalendarSynchronizationSupervisor supervisor) {
		getEntityManager().persist(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(CalendarSynchronizationSupervisor supervisor) {
		getEntityManager().merge(supervisor);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public CalendarSynchronizationSupervisor get() {
		CalendarSynchronizationSupervisor supervisor = null;
		String query = "FROM CalendarSynchronizationSupervisor";
		
		supervisor = (CalendarSynchronizationSupervisor)getEntityManager()
				.createQuery(query)
				.getSingleResult();
		
		getEntityManager().flush();
		getEntityManager().clear();
		
		return supervisor;
	}

	
}
