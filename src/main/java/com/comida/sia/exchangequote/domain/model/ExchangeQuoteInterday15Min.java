package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Interday60MinPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteInterday15Min")
@DiscriminatorValue("INTERDAY_15")
public class ExchangeQuoteInterday15Min extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779982282205089104L;
	
	public ExchangeQuoteInterday15Min() {
		super();
	}

	public ExchangeQuoteInterday15Min(Builder<ExchangeQuoteInterday15Min> builder) {
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
	public static class ExchangeQuoteInterday15MinBuilder extends Builder<ExchangeQuoteInterday15Min>{
		private Period period;
		
		public ExchangeQuoteInterday15MinBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteInterday15Min> period(Date date) {
			period = new Interday60MinPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteInterday15Min build() {
			return new ExchangeQuoteInterday15Min(this);
		}
	}
}
