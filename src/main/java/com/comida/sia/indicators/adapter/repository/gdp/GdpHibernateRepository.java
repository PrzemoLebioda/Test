package com.comida.sia.indicators.adapter.repository.gdp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.gdp.GrossDomesticProduct;
import com.comida.sia.indicators.port.repository.gdp.GdpRepository;

import jakarta.persistence.EntityManager;

@Component("GdpHibernateRepository")
public class GdpHibernateRepository extends HibernateRepository implements GdpRepository {

	public GdpHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(GrossDomesticProduct gdp) {
		getEntityManager().persist(gdp);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(GrossDomesticProduct gdp) {
		getEntityManager().merge(gdp);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GrossDomesticProduct> getFor() {
		List<GrossDomesticProduct> grossDomesticProductList;
		
		String query = "FROM GrossDomesticProductGeneral";
	
		grossDomesticProductList = getEntityManager()
				.createQuery(query)
				.getResultList();
		
		getEntityManager().detach(grossDomesticProductList);
	
		return grossDomesticProductList;
	}

}
