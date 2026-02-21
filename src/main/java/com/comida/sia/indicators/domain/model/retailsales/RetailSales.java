package com.comida.sia.indicators.domain.model.retailsales;

import java.util.UUID;

import com.comida.sia.indicators.domain.model.Indicator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="RetailSales")
@DiscriminatorValue("RETAIL_SALES")
public class RetailSales extends Indicator{
	
	public RetailSales() {
		super();
	}

	public RetailSales(RetailSalesBuilder builder) {
		super(builder.getId());
		
		setInterval(builder.getInterval());
		setDate(builder.getDate());
		setValue(builder.getValue());
	}
	
	public static class RetailSalesBuilder extends Builder<RetailSales>{

		public RetailSalesBuilder(UUID id) {
			super(id);
		}

		@Override
		public RetailSales build() {
			return new RetailSales(this);
		}
	}
}
