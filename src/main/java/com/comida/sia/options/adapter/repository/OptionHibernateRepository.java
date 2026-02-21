package com.comida.sia.options.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.options.domain.model.Option;
import com.comida.sia.options.port.repository.OptionRepository;

import jakarta.persistence.EntityManager;

@Component("OptionHibernateRepository")
public class OptionHibernateRepository extends HibernateRepository implements OptionRepository{

	public OptionHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(Option option) {
		getEntityManager().persist(option);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(Option option) {
		getEntityManager().merge(option);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Option> getFor(String tickerSybmol) {
		List<Option> optionsList;
		
		String query = "FROM Option "
				 	 + "WHERE tickerSymbol = :tickerSybmol";
	
		optionsList = getEntityManager()
				.createQuery(query)
				.setParameter("tickerSybmol", tickerSybmol)
				.getResultList();
		
		getEntityManager().detach(optionsList);
	
		return optionsList;
	}
}
