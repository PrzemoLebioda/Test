package com.comida.sia.indicators.adapter.repository.durable;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.indicators.domain.model.durables.DurableGoodsOrders;
import com.comida.sia.indicators.port.repository.durables.DurableGoodsOrdersRepository;

import jakarta.persistence.EntityManager;

@Component("DurableGoodsOrdersHibernateRepository")
public class DurableGoodsOrdersHibernateRepository extends HibernateRepository implements DurableGoodsOrdersRepository {

	public DurableGoodsOrdersHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(DurableGoodsOrders durableGoodsOrders) {
		getEntityManager().persist(durableGoodsOrders);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void update(DurableGoodsOrders durableGoodsOrders) {
		getEntityManager().merge(durableGoodsOrders);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
