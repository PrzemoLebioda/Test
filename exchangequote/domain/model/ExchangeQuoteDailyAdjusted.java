package com.comida.sia.exchangequote.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.DailyPeriod;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity(name="ExchangeQuoteDailyAdjusted")
@DiscriminatorValue("DAILY_ADJUSTED")
public class ExchangeQuoteDailyAdjusted extends ExchangeQuote{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7468422491225515293L;
	
	public ExchangeQuoteDailyAdjusted() {
		super();
	}
	
	public ExchangeQuoteDailyAdjusted(Builder<ExchangeQuoteDailyAdjusted> builder) {
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
	public static class ExchangeQuoteDailyAdjustedBuilder extends Builder<ExchangeQuoteDailyAdjusted>{

		private Period period;
		
		public ExchangeQuoteDailyAdjustedBuilder(UUID id) {
			super(id);
		}

		@Override
		public Builder<ExchangeQuoteDailyAdjusted> period(Date date) {
			period = new DailyPeriod(date);
			return this;
		}

		@Override
		public ExchangeQuoteDailyAdjusted build() {
			return new ExchangeQuoteDailyAdjusted(this);
		}
		
	}
}
