package com.comida.sia.sharedkernel.period;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "INTERDAY_60_PERIOD" )
public class Interday60MinPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5973320335750681390L;

	public Interday60MinPeriod() {
		super();
	}
	
	public Interday60MinPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}
	
	private static String calculatePeriodValueFrom(Date date) {
		AssertionConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";		
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
		periodValue = periodValue 
				+ localDateTime.getYear()
				+ "-" + makeString(localDateTime.getMonthValue()) 
				+ "-" + makeString(localDateTime.getDayOfMonth())
				+ "-" + makeString(localDateTime.getHour());
		
		return periodValue;
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.INTERDAY_60_MINUTE_INTERVAL;
	}

	@Override
	public String getFormatted() {
		return periodValue;
	}

	@Override
	public Boolean isCurrent() {
		Integer year = getYear();
		Integer month = getMonth();
		Integer day = getDay();
		Integer hour = getHour();
		
		if(year == null || month == null || day == null || hour == null) {
			return false;
		}
	
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) 
				&& month.equals(localDateTime.getMonthValue()) 
				&& day.equals(localDateTime.getDayOfMonth()) 
				&& hour.equals(localDateTime.getHour())){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		Interday60MinPeriod period = new Interday60MinPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new Interday60MinPeriod(new Date()));
	}

	@Override
	public Date beginsAt() {
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						Month.of(getMonth()), 
						getDay(), 
						getHour(), 
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
						getDay(), 
						getHour(), 
						59, 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public Period next() {
		return new Interday60MinPeriod(
						Date.from(
							LocalDateTime
								.of(
									getYear(), 
									Month.of(getMonth()),  
									getDay(), 
									getHour(), 
									0, 
									0, 
									0
								)
								.plusHours(1)
								.atZone(ZoneId.systemDefault())
								.toInstant()
							)
					);
	}

	@Override
	public Period previous() {
		return new Interday60MinPeriod(
				Date.from(
					LocalDateTime
						.of(
							getYear(), 
							Month.of(getMonth()), 
							getDay(), 
							getHour(), 
							0, 
							0, 
							0
						)
						.minusHours(1)
						.atZone(ZoneId.systemDefault())
						.toInstant()
					)
			);
	}

	@Override
	public Period move(Date date) {
		return new Interday60MinPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {	
		LocalDateTime currentPeriodStartsAt = LocalDateTime
							.of(
								getYear(), 
								Month.of(getMonth()),  
								getDay(), 
								getHour(), 
								0, 
								0, 
								0
							);	
		
		return Duration.between(getStartMoment(), currentPeriodStartsAt).toHours();
	}
	
	private Integer getYear() {
		return Integer.valueOf(getFormatted().substring(0, 4));
	}
	
	private Integer getMonth() {
		return Integer.valueOf(getFormatted().substring(5, 7));
	}
	
	private Integer getDay() {
		return Integer.valueOf(getFormatted().substring(8, 10));
	}
	
	private Integer getHour() {
		return Integer.valueOf(getFormatted().substring(11, 13));
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
