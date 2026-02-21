package com.comida.sia.indicators.domain.model.inflation;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="Inflation")
@DiscriminatorValue("INFLATION")
public class Inflation extends Indicator {
	
	public Inflation() {
		super();
	}
	
	public Inflation(Builder<Inflation> builder) {
		super(builder.getId());
		
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}

	public static class InflationBuilder extends Builder<Inflation>{

		public InflationBuilder(UUID id) {
			super(id);
		}

		@Override
		public Inflation build() {
			return new Inflation(this);
		}
	}
}
