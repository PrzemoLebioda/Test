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
@DiscriminatorValue( "MONTHLY_PERIOD" )
public class MonthlyPeriod extends Period {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5054675957884996931L;
	
	public MonthlyPeriod() {
		super();
	}
	
	
	public MonthlyPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}
	
	private static String calculatePeriodValueFrom(Date date) {
		TranslationConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
		periodValue = periodValue 
				+ localDateTime.getYear()
				+ "-" + makeString(localDateTime.getMonthValue());
		
		return periodValue;
	}
	
	public MonthlyPeriod(String period) {
		super(period);	
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.MONTHLY;
	}
	
	@Override
	public String getFormatted() {
		return periodValue;
	}
	
	@Override
	public Boolean isCurrent() {
		Integer year = getYear();
		Integer month = getMonth();
		
		if(year == null || month == null) {
			return false;
		}
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) && month.equals(localDateTime.getMonthValue())){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		MonthlyPeriod period = new MonthlyPeriod(date);
		
		return this.sameValueAs(period);
	}
	
	@Override
	public Long getPeriodOrderNumber() {
		LocalDate currentPeriodStartsAt = LocalDate
				.of(
					getYear(), 
					Month.of(getMonth()), 
					1);
		
		int months = java.time.Period.between(getStartMoment().toLocalDate(), currentPeriodStartsAt).getMonths();
				
		return Long.valueOf(months);
	}
	
	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new MonthlyPeriod(new Date()));
	}
	
	@Override
	public Date beginsAt() {		
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						Month.of(getMonth()), 
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
						Month.of(getMonth()), 
						YearMonth.of(getYear(), Month.of(getMonth())).atEndOfMonth().getDayOfMonth(), 
						23, 
						59, 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public MonthlyPeriod next() {		
		return new MonthlyPeriod(
				Date.from(
						LocalDateTime
							.of(
								getYear(), 
								getMonth(), 
								1, 
								0, 
								0, 
								0, 
								0
							)
							.plusMonths(1)
							.atZone(ZoneId.systemDefault())
							.toInstant()
						)
				);
	}

	@Override
	public Period previous() {
		return new MonthlyPeriod(
				Date.from(
						LocalDateTime
							.of(
								getYear(), 
								getMonth(), 
								1, 
								0, 
								0, 
								0, 
								0
							)
							.minusMonths(1)
							.atZone(ZoneId.systemDefault())
							.toInstant()
						)
				);
	}

	@Override
	public Period move(Date date) {
		return new MonthlyPeriod(date);
	}
	
	private Integer getYear() {
		return Integer.valueOf(getFormatted().substring(0, 4));
	}
	
	private Integer getMonth() {
		return Integer.valueOf(getFormatted().substring(5, 7));
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
