package com.comida.sia.indicators.domain.model.payroll;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="NonfarmPayroll")
@DiscriminatorValue("NONFARM_PAYROLL")
public class NonfarmPayroll extends Indicator {

	public NonfarmPayroll() {
		super();
	}
	
	public NonfarmPayroll(Builder<NonfarmPayroll> builder) {
		super(builder.getId());
		
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class NonfarmPayrollBuilder extends Builder<NonfarmPayroll>{

		public NonfarmPayrollBuilder(UUID id) {
			super(id);
		}

		@Override
		public NonfarmPayroll build() {
			return new NonfarmPayroll(this);
		}
	}
}
