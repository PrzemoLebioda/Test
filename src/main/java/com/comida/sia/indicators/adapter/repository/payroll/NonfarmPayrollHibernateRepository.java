package com.comida.sia.indicators.adapter.repository.payroll;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.payroll.NonfarmPayroll;
import com.comida.sia.indicators.port.repository.payroll.NonfarmPayrollRepository;

import jakarta.persistence.EntityManager;

@Component("NonfarmPayrollHibernateRepository")
public class NonfarmPayrollHibernateRepository extends HibernateRepository implements NonfarmPayrollRepository{

	public NonfarmPayrollHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(NonfarmPayroll nonfarmPayroll) {
		getEntityManager().persist(nonfarmPayroll);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(NonfarmPayroll nonfarmPayroll) {
		getEntityManager().merge(nonfarmPayroll);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
