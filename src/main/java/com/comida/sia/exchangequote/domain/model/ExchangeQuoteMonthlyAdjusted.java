package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.MonthlyPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteMonthlyAdjusted")
@DiscriminatorValue("MONTHLY_ADJUSTED")
public class ExchangeQuoteMonthlyAdjusted extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846809365817663349L;

	public ExchangeQuoteMonthlyAdjusted() {
		super();
	}
	
	public ExchangeQuoteMonthlyAdjusted(Builder<ExchangeQuoteMonthlyAdjusted> builder) {
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
	public static class ExchangeQuoteMonthlyAdjustedBuilder extends Builder<ExchangeQuoteMonthlyAdjusted>{
		private Period period;
		
		public ExchangeQuoteMonthlyAdjustedBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteMonthlyAdjusted> period(Date date) {
			period = new MonthlyPeriod(date);
			return this;
		}

		@Override
		public ExchangeQuoteMonthlyAdjusted build() {
			return new ExchangeQuoteMonthlyAdjusted(this);
		}
		
	}
}
