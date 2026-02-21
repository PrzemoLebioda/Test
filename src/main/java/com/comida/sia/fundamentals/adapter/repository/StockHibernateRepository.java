package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.stock.Stock;
import com.comida.sia.fundamentals.port.repository.StockRepository;

import jakarta.persistence.EntityManager;

@Component("StockHibernateRepository")
public class StockHibernateRepository extends HibernateRepository implements StockRepository {

	public StockHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(Stock stock) {
		getEntityManager().persist(stock);
		
		getEntityManager().flush();
		getEntityManager().clear();	
		
	}

	@Override
	public void update(Stock stock) {
		getEntityManager().merge(stock);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public Stock get(String tickerSymbol) {
		Stock stock;
		
		String query = "FROM Stock "
				 	 + "WHERE tickerSymbol = :tickerSymbol";
	
		stock = (Stock)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		getEntityManager().detach(stock);
	
		return stock;
	}

}
