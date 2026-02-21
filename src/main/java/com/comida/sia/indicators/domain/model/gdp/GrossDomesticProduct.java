package com.comida.sia.indicators.domain.model.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="GrossDomesticProduct")
@DiscriminatorValue("GDP")
public class GrossDomesticProduct extends Indicator {
	
	public GrossDomesticProduct() {
		super();
	}
	
	public GrossDomesticProduct(Builder<GrossDomesticProduct> builder) {
		super(builder.getId());
	
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class GDPBuilder extends Builder<GrossDomesticProduct>{

		public GDPBuilder(UUID id) {
			super(id);
		}

		@Override
		public GrossDomesticProduct build() {
			return new GrossDomesticProduct(this);
		}
	}
}
