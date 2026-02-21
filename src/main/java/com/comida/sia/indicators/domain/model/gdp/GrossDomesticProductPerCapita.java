package com.comida.sia.indicators.domain.model.gdp;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="GrossDomesticProductPerCapita")
@DiscriminatorValue("GDP_PER_CAPITA")
public class GrossDomesticProductPerCapita extends Indicator {
	
	public GrossDomesticProductPerCapita() {
		super();
	}
	
	public GrossDomesticProductPerCapita(Builder<GrossDomesticProductPerCapita> builder) {
		super(builder.getId());
	
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class GDPBuilder extends Builder<GrossDomesticProductPerCapita>{

		public GDPBuilder(UUID id) {
			super(id);
		}
		
		@Override
		public GrossDomesticProductPerCapita build() {
			return new GrossDomesticProductPerCapita(this);
		}
	}
}
