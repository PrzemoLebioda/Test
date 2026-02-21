package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Interday60MinPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteInterday01Min")
@DiscriminatorValue("INTERDAY_01")
public class ExchangeQuoteInterday01Min extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4825987596161287114L;
	
	public ExchangeQuoteInterday01Min() {
		super();
	}

	public ExchangeQuoteInterday01Min(Builder<ExchangeQuoteInterday01Min> builder) {
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
	public static class ExchangeQuoteInterday01MinBuilder extends Builder<ExchangeQuoteInterday01Min>{
		private Period period;
		
		public ExchangeQuoteInterday01MinBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteInterday01Min> period(Date date) {
			period = new Interday60MinPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteInterday01Min build() {
			return new ExchangeQuoteInterday01Min(this);
		}
	}
}
