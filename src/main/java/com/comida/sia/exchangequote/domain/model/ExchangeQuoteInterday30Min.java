package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Interday60MinPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteInterday30Min")
@DiscriminatorValue("INTERDAY_30")
public class ExchangeQuoteInterday30Min extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2492864717605611265L;
	
	public ExchangeQuoteInterday30Min() {
		super();
	}
	
	public ExchangeQuoteInterday30Min(Builder<ExchangeQuoteInterday30Min> builder) {
		setId(builder.getId());
		setExchange(builder.getExchange());
		setSector(builder.getSector());
		setIndustry(builder.getIndustry());
		setTickerSymbol(builder.getTickerSymbol());
		setPeriod(builder.getPeriod());
		setQuotationTime(builder.getQuotationTime());
		setOpen(builder.getOpen());
		setHigh(builder.getHigh());
		setLow(builder.getLow());
		setClose(builder.getClose());
		setVolume(builder.getVolume()); 
		setAdjustedClose(builder.getAdjustedClose());
		setDividendAmount(builder.getDividendAmount());
		setSplitCoefficient(builder.getSplitCoefficient());
	}

	@Getter
	public static class ExchangeQuoteInterday30MinBuilder extends Builder<ExchangeQuoteInterday30Min>{
		private Period period;
		
		public ExchangeQuoteInterday30MinBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteInterday30Min> period(Date date) {
			period = new Interday60MinPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteInterday30Min build() {
			return new ExchangeQuoteInterday30Min(this);
		}
	}
}
