package com.comida.sia.intelligence.insidertransactions.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransaction;
import com.comida.sia.intelligence.insidertransactions.port.repository.InsiderTransactionRepository;

import jakarta.persistence.EntityManager;

@Component("InsiderTransactionHibernateRepository")
public class InsiderTransactionHibernateRepository extends HibernateRepository implements InsiderTransactionRepository{

	public InsiderTransactionHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}


	@Override
	public void store(InsiderTransaction insiderTransaction) {
		getEntityManager().persist(insiderTransaction);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(InsiderTransaction insiderTransaction) {
		getEntityManager().merge(insiderTransaction);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InsiderTransaction> getFor(String tickerSybmol) {
		List<InsiderTransaction> insiderTransactionList;
		
		String query = "FROM InsiderTransaction "
				 	 + "WHERE tickerSymbol = :tickerSybmol";
	
		insiderTransactionList = getEntityManager()
				.createQuery(query)
				.setParameter("tickerSybmol", tickerSybmol)
				.getResultList();
		
		getEntityManager().detach(insiderTransactionList);
	
		return insiderTransactionList;
	}

}
