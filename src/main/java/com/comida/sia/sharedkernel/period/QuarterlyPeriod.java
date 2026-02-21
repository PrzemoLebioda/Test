package com.comida.sia.sharedkernel.period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "QUARTERLY_PERIOD" )
public class QuarterlyPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3035412189322907376L;
	
	public QuarterlyPeriod() {
		super();
	}
	
	public QuarterlyPeriod(String period) {
		super(period);
	}
	
	public QuarterlyPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}
	
	private static String calculatePeriodValueFrom(Date date) {
		TranslationConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";
		
		LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
		int year = dateTime.getYear();
		int month = dateTime.getMonthValue();
		
		if(month >= 1 && month < 4) {
			periodValue = year + "-1";
		}
		
		if(month >=4 && month < 7) {
			periodValue = year + "-2";
		}
		
		if(month >=7 && month < 10) {
			periodValue = year + "-3";
		}
		
		if(month >= 10 && month <= 12) {
			periodValue = year + "-4";
		}
		
		return periodValue;
	}

	@Override
	public PeriodType getType() {
		return PeriodType.QUARTERLY;
	}

	@Override
	public String getFormatted() {
		return periodValue;
	}

	@Override
	public Boolean isCurrent() {
		Integer year = getYear();
		Integer quarter = getQuarter();
		
		if(year == null || quarter == null) {
			return false;
		}

		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) && quarter.equals(getQuarter(localDateTime.getMonthValue()))){
			return true;
		}else {
			return false;
		}
	}
	
	private Integer getYear() {
		return Integer.valueOf(getFormatted().substring(0, 4));
	}
	
	private Integer getQuarter() {
		return Integer.valueOf(getFormatted().substring(5, 6));
	}
	
	private Integer getQuarter(Integer month) {
		if(month >= 1 && month < 4) {
			return 1;
		}
		
		if(month >=4 && month < 7) {
			return 2;
		}
		
		if(month >=7 && month < 10) {
			return 3;
		}
		
		if(month >= 10 && month <= 12) {
			return 4;
		}
		
		return 0;
	}

	@Override
	public Boolean contains(Date date) {
		QuarterlyPeriod period = new QuarterlyPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new QuarterlyPeriod(new Date()));
	}

	@Override
	public Date beginsAt() {				
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						Month.of(getQuarter() * 3 - 2), 
						1, 
						0, 
						0, 
						0, 
						0)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public Date endsAt() {		
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						Month.of(getQuarter() * 3), 
						YearMonth.of(getYear(), Month.of(getQuarter() * 3)).atEndOfMonth().getDayOfMonth(), 
						23, 
						59, 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public Period next() {
		QuarterlyPeriod nextPeriod;
		
		Integer year = getYear();
		Integer quarter = getQuarter();
		
		if(quarter.equals(4)) {
			nextPeriod = new QuarterlyPeriod(buildPeriodStringFrom(year + 1, 1)); 
		} else {
			nextPeriod = new QuarterlyPeriod(buildPeriodStringFrom(year, quarter + 1));
		}
		 
		return nextPeriod;
	}	

	@Override
	public Period previous() {
		QuarterlyPeriod prevoiusPeriod;
		
		Integer year = getYear();
		Integer quarter = getQuarter();
		
		if(quarter.equals(1)) {
			prevoiusPeriod = new QuarterlyPeriod(buildPeriodStringFrom(year - 1, 4));
		} else {
			prevoiusPeriod = new QuarterlyPeriod(buildPeriodStringFrom(year, quarter - 1));
		}
		
		return prevoiusPeriod;
	}
	
	private String buildPeriodStringFrom(Integer year, Integer quarter) {
		String period = "";
		
		period = period + year + "-" + quarter;
		
		return period;
	}

	@Override
	public Period move(Date date) {
		return new QuarterlyPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {
		LocalDate currentPeriodStartsAt = LocalDate
											.of(
												getYear(), 
												Month.of(getQuarter() * 3 - 2), 
												1);
		
		int years = java.time.Period.between(getStartMoment().toLocalDate(), currentPeriodStartsAt).getYears();
		int months = java.time.Period.between(getStartMoment().toLocalDate(), currentPeriodStartsAt).getMonths();
		int quarters = (years * 12 + months)/3;
		
		return Long.valueOf(quarters);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonthlyPeriod other = (MonthlyPeriod) obj;
		return sameValueAs(other);
	}
}
