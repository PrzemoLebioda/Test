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
@DiscriminatorValue( "INTERDAY_15_PERIOD" )
public class Interday15MinPeriod extends Period{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6021262234979991755L;

	public Interday15MinPeriod() {
		super();
	}
	
	public Interday15MinPeriod(Date date) {
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
				+ "-" + makeString(getPeriodNo(localDateTime.getMinute()));
		
		return periodValue;
	}
	
	
	
	@Override
	public PeriodType getType() {
		return PeriodType.INTERDAY_15_MINUTE_INTERVAL;
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
				&& minPeriod.equals(getPeriodNo(localDateTime.getMinute()))){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		Interday15MinPeriod period = new Interday15MinPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new Interday15MinPeriod(new Date()));
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
						getPeriodFirstMinute(getMinPeriod()), 
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
						getPeriodLastMinute(getMinPeriod()), 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	@Override
	public Period next() {
		return new Interday15MinPeriod(
					Date.from(
						LocalDateTime
							.of(
								getYear(), 
								Month.of(getMonth()),  
								getDay(), 
								getHour(), 
								getPeriodLastMinute(getMinPeriod()), 
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
		return new Interday15MinPeriod(
				Date.from(
					LocalDateTime
						.of(
							getYear(), 
							Month.of(getMonth()),  
							getDay(), 
							getHour(), 
							getPeriodFirstMinute(getMinPeriod()), 
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
		return new Interday15MinPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {
		LocalDateTime currentPeriodStartsAt = LocalDateTime
				.of(
					getYear(), 
					Month.of(getMonth()),  
					getDay(), 
					getHour(), 
					getPeriodFirstMinute(getMinPeriod()), 
					0, 
					0
				);	

		return Duration.between(getStartMoment(), currentPeriodStartsAt).toMinutes()/15;
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
	
	private static int getPeriodNo(int minute) {
		if(minute >= 0 && minute < 15) {
			return 1;
		} else if(minute >= 15 && minute < 30) {
			return 2;
		} else if(minute >= 30 && minute < 45) {
			return 3;
		} else if(minute >= 45 && minute < 60) {
			return 4;
		} else {
			throw new IllegalArgumentException("Argument out of range. Expected values: <0, 59>");
		}
	}
	
	private static int getPeriodFirstMinute(int periodNo) {
		if(periodNo == 1) {
			return 0;
		} else if(periodNo == 2) {
			return 15;
		} else if(periodNo == 3) {
			return 30;
		} else if(periodNo == 4) {
			return 45;
		} else {
			throw new IllegalArgumentException("Argument out of range. Expected values: <1, 4>");
		}
	}
	
	private static int getPeriodLastMinute(int periodNo) {
		if(periodNo == 1) {
			return 14;
		} else if(periodNo == 2) {
			return 29;
		} else if(periodNo == 3) {
			return 44;
		} else if(periodNo == 4) {
			return 59;
		} else {
			throw new IllegalArgumentException("Argument out of range. Expected values: <1, 4>");
		}
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
