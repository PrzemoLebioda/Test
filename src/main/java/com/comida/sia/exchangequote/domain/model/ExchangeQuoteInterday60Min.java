package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Interday60MinPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteInterday60Min")
@DiscriminatorValue("INTERDAY_60")
public class ExchangeQuoteInterday60Min extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1715417278523492170L;

	public ExchangeQuoteInterday60Min() {
		super();
	}
	
	public ExchangeQuoteInterday60Min(Builder<ExchangeQuoteInterday60Min> builder) {
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
	public static class ExchangeQuoteInterday60MinBuilder extends Builder<ExchangeQuoteInterday60Min>{
		private Period period;
		
		public ExchangeQuoteInterday60MinBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteInterday60Min> period(Date date) {
			period = new Interday60MinPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteInterday60Min build() {
			return new ExchangeQuoteInterday60Min(this);
		}
		
	}
}
