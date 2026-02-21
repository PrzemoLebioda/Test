package com.comida.sia.indicators.domain.model.durables;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="DurableGoodsOrders")
@DiscriminatorValue("DURABLES")
public class DurableGoodsOrders extends Indicator {
	
	public DurableGoodsOrders() {
		super();
	}
	
	public DurableGoodsOrders(Builder<DurableGoodsOrders> builder) {
		super(builder.getId());
		
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class DurablesBuilder extends Builder<DurableGoodsOrders>{

		public DurablesBuilder(UUID id) {
			super(id);
		}

		@Override
		public DurableGoodsOrders build() {
			return new DurableGoodsOrders(this);
		}
	}
}
