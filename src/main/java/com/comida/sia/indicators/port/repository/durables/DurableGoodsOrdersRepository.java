package com.comida.sia.indicators.port.repository.durables;

import com.comida.sia.indicators.domain.model.durables.DurableGoodsOrders;

public interface DurableGoodsOrdersRepository {
	public void store(DurableGoodsOrders durableGoodsOrders);
	public void update(DurableGoodsOrders durableGoodsOrders);
}
