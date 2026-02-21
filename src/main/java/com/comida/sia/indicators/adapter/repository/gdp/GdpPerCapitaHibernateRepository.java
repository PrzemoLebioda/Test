package com.comida.sia.indicators.adapter.repository.gdp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.gdp.GrossDomesticProductPerCapita;
import com.comida.sia.indicators.port.repository.gdp.GdpPerCapitaRepository;

import jakarta.persistence.EntityManager;

@Component("GdpPerCapitaHibernateRepository")
public class GdpPerCapitaHibernateRepository extends HibernateRepository implements GdpPerCapitaRepository{

	public GdpPerCapitaHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(GrossDomesticProductPerCapita gdp) {
		getEntityManager().persist(gdp);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(GrossDomesticProductPerCapita gdp) {
		getEntityManager().merge(gdp);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrossDomesticProductPerCapita> getFor() {
		List<GrossDomesticProductPerCapita> grossDomesticProductList;
		
		String query = "FROM GrossDomesticProductPerCapita";
	
		grossDomesticProductList = getEntityManager()
				.createQuery(query)
				.getResultList();
		
		getEntityManager().detach(grossDomesticProductList);
	
		return grossDomesticProductList;
	}

}
