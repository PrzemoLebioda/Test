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
@DiscriminatorValue( "INTERDAY_30_PERIOD" )
public class Interday30MinPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6588483372814015486L;

	public Interday30MinPeriod() {
		super();
	}
	
	public Interday30MinPeriod(Date date) {
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
				+ "-" + makeString(localDateTime.getHour())
				+ "-" + makeString(localDateTime.getMinute() < 30 ? 1 : 2);
		
		return periodValue;
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.INTERDAY_30_MINUTE_INTERVAL;
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
		Integer minPeriod = getMinPeriod();
		
		if(year == null || month == null || day == null || hour == null || minPeriod == null) {
			return false;
		}
	
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) 
				&& month.equals(localDateTime.getMonthValue()) 
				&& day.equals(localDateTime.getDayOfMonth()) 
				&& hour.equals(localDateTime.getHour())
				&& minPeriod.equals(localDateTime.getMinute() < 30 ? 1 : 2)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		Interday30MinPeriod period = new Interday30MinPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new Interday30MinPeriod(new Date()));
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
						getMinPeriod() == 1 ? 0 : 30, 
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
						getMinPeriod() == 1 ? 29 : 59, 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public Period next() {
		return new Interday30MinPeriod(
					Date.from(
						LocalDateTime
							.of(
								getYear(), 
								Month.of(getMonth()),  
								getDay(), 
								getHour(), 
								getMinPeriod() == 1 ? 29 : 59, 
								0, 
								0
							)
							.plusMinutes(1)
							.atZone(ZoneId.systemDefault())
							.toInstant()
						)
				);
	}

	@Override
	public Period previous() {
		return new Interday30MinPeriod(
				Date.from(
					LocalDateTime
						.of(
							getYear(), 
							Month.of(getMonth()),  
							getDay(), 
							getHour(), 
							getMinPeriod() == 1 ? 0 : 30, 
							0, 
							0
						)
						.minusMinutes(1)
						.atZone(ZoneId.systemDefault())
						.toInstant()
					)
			);
	}

	@Override
	public Period move(Date date) {
		return new Interday30MinPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {
		LocalDateTime currentPeriodStartsAt = LocalDateTime
				.of(
					getYear(), 
					Month.of(getMonth()),  
					getDay(), 
					getHour(), 
					getMinPeriod() == 1 ? 0 : 30, 
					0, 
					0
				);	

		return Duration.between(getStartMoment(), currentPeriodStartsAt).toMinutes()/30;
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
	
	private Integer getMinPeriod() {
		return Integer.valueOf(getFormatted().substring(14, 16));
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
