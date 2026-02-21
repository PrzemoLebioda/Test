package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sharedkernel.period.WeeklyPeriod;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteWeeklyAdjusted")
@DiscriminatorValue("WEEKLY_ADJUSTED")
public class ExchangeQuoteWeeklyAdjusted extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1849494668378786185L;

	public ExchangeQuoteWeeklyAdjusted() {
		super();
	}
	
	public ExchangeQuoteWeeklyAdjusted(Builder<ExchangeQuoteWeeklyAdjusted> builder) {
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
	public static class ExchangeQuoteWeeklyAdjustedBuilder extends Builder<ExchangeQuoteWeeklyAdjusted>{
		private Period period;
		
		public ExchangeQuoteWeeklyAdjustedBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteWeeklyAdjusted> period(Date date) {
			period = new WeeklyPeriod(date);
			return this;
		}
		
		@Override
		public ExchangeQuoteWeeklyAdjusted build() {
			return new ExchangeQuoteWeeklyAdjusted(this);
		}
		
	}
}
