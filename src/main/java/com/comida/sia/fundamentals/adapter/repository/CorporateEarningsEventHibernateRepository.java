package com.comida.sia.fundamentals.adapter.repository;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.CorporateEarningEvent;
import com.comida.sia.fundamentals.port.repository.CorporateEarningsEventRepository;

import jakarta.persistence.EntityManager;

@Component("CorporateEarningsEventHibernateRepository")
public class CorporateEarningsEventHibernateRepository extends HibernateRepository implements CorporateEarningsEventRepository{

	public CorporateEarningsEventHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(CorporateEarningEvent event) {
		getEntityManager().persist(event);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(CorporateEarningEvent event) {
		getEntityManager().merge(event);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public CorporateEarningEvent get(String tickerSymbol, Date fiscalDateEnding) {
		CorporateEarningEvent corporateEarningEvent;
		
		String query = "FROM CorporateEarningEvent "
				 	 + "WHERE tickerSymbol = :tickerSymbol"
				 	 + "	  AND"
				 	 + "	  fiscalDateEnding = :fiscalDateEnding";
	
		corporateEarningEvent = (CorporateEarningEvent)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.setParameter("fiscalDateEnding", fiscalDateEnding)
				.getSingleResult();
		
		getEntityManager().detach(corporateEarningEvent);
	
		return corporateEarningEvent;
	}

}
