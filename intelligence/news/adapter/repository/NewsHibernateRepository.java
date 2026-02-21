package com.comida.sia.intelligence.news.adapter.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.intelligence.news.domain.model.NewsFeed;
import com.comida.sia.intelligence.news.port.repository.NewsRepository;

import jakarta.persistence.EntityManager;

@Component("NewsHibernateRepository")
public class NewsHibernateRepository extends HibernateRepository implements NewsRepository{

	public NewsHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(NewsFeed newsFeed) {
		getEntityManager().persist(newsFeed);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(NewsFeed newsFeed) {
		getEntityManager().merge(newsFeed);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsFeed> getFor(String tickerSybmol) {
		List<NewsFeed> newsFeedsList;
		
		String query = "FROM"
				+ "			NewsFeed n"
				+ "			JOIN FETCH n.tickerSentiments ts"
				+ "		WHERE"
				+ "			ts.tickerSymbol = :tickerSybmol";
	
		newsFeedsList = getEntityManager()
				.createQuery(query)
				.setParameter("tickerSybmol", tickerSybmol)
				.getResultList();
		
		getEntityManager().detach(newsFeedsList);
	
		return newsFeedsList;
	}

}
