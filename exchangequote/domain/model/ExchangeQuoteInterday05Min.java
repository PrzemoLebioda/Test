package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Interday60MinPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteInterday05Min")
@DiscriminatorValue("INTERDAY_05")
public class ExchangeQuoteInterday05Min extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = -146682553092537244L;
	
	public ExchangeQuoteInterday05Min() {
		super();
	}
	
	public ExchangeQuoteInterday05Min(Builder<ExchangeQuoteInterday05Min> builder) {
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
	public static class ExchangeQuoteInterday05MinBuilder extends Builder<ExchangeQuoteInterday05Min>{
		private Period period;
		
		public ExchangeQuoteInterday05MinBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteInterday05Min> period(Date date) {
			period = new Interday60MinPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteInterday05Min build() {
			return new ExchangeQuoteInterday05Min(this);
		}
	}
}
