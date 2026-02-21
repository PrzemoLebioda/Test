package com.comida.sia.indicators.domain.model.cpi;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="ConsumerPriceIndex")
@DiscriminatorValue("CPI")
public class ConsumerPriceIndex extends Indicator {

	public ConsumerPriceIndex(){
		super();
	}
	
	public ConsumerPriceIndex(Builder<ConsumerPriceIndex> builder) {
		super(builder.getId());
	
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class CPIBuilder extends Builder<ConsumerPriceIndex>{

		public CPIBuilder(UUID id) {
			super(id);
		}

		@Override
		public ConsumerPriceIndex build() {
			return new ConsumerPriceIndex(this);
		}
	}
}
