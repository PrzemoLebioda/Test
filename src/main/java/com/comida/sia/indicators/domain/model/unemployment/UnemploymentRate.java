package com.comida.sia.indicators.domain.model.unemployment;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="UnemploymentRate")
@DiscriminatorValue("UNEMPLOYMENT_RATE")
public class UnemploymentRate extends Indicator {

	public UnemploymentRate() {
		super();
	}
	
	public UnemploymentRate(Builder<UnemploymentRate> builder) {
		super(builder.getId());
		
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class UnemploymentRateBuilder extends Builder<UnemploymentRate>{

		public UnemploymentRateBuilder(UUID id) {
			super(id);
		}

		@Override
		public UnemploymentRate build() {
			return new UnemploymentRate(this);
		}
	}
}
