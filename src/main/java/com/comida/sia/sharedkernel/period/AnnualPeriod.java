package com.comida.sia.sharedkernel.period;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.comida.sia.sharedkernel.TranslationConcern;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "ANNUAL_PERIOD" )
public class AnnualPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6605028051583582251L;
	
	public AnnualPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}

	private static String calculatePeriodValueFrom(Date date) {
		TranslationConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";
		periodValue = periodValue + LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).getYear();
		
		return periodValue;
	}
	
	public AnnualPeriod(String period) {
		super(period);
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.ANNUAL;
	}

	@Override
	public String getFormatted() {
		return periodValue;
	}

	@Override
	public Boolean isCurrent() {
		Integer year = getYear();
		
		if(year == null) {
			return false;
		}
		
		Calendar calendar = GregorianCalendar.getInstance();
		
		if(year.equals(calendar.get(Calendar.YEAR))){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		AnnualPeriod period = new AnnualPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Boolean notContains(Date date) {
		return !contains(date); 
	}

	@Override
	public Long getPeriodOrderNumber() {
		return Long.valueOf(getYear());
	}
	
	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new AnnualPeriod(new Date()));
	}

	@Override
	public Date beginsAt() {
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						1, 
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
						12, 
						31, 
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
		return new AnnualPeriod(buildPeriodStringFrom(getYear() + 1));
	}
	
	@Override
	public Period previous() {
		return new AnnualPeriod(buildPeriodStringFrom(getYear() - 1));
	}
	
	private String buildPeriodStringFrom(Integer year) {
		String period = "";		
		return period + year;
	}

	@Override
	public Period move(Date date) {
		return new  AnnualPeriod(date);
	}
	
	private Integer getYear() {
		return Integer.valueOf(getFormatted().substring(0, 4));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnualPeriod other = (AnnualPeriod) obj;
		return sameValueAs(other);
	}
}
