package com.comida.sia.indicators.domain.model.intrestrate;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="IntrestRate")
@DiscriminatorValue("INTREST_RATE")
public class IntrestRate extends Indicator {

	public IntrestRate() {
		super();
	}
	
	public IntrestRate(Builder<IntrestRate> builder) {
		super(builder.getId());
	
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class IntrestRateBuilder extends Builder<IntrestRate>{

		public IntrestRateBuilder(UUID id) {
			super(id);
		}

		@Override
		public IntrestRate build() {
			return new IntrestRate(this);
		}
	}
}
