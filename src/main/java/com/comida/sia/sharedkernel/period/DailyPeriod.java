package com.comida.sia.sharedkernel.period;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "DAILY_PERIOD" )
public class DailyPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2099579557838799954L;

	public DailyPeriod() {
		super();
	}
	
	public DailyPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}
	
	private static String calculatePeriodValueFrom(Date date) {
		AssertionConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";		
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
		periodValue = periodValue 
				+ localDateTime.getYear()
				+ "-" + makeString(localDateTime.getMonthValue()) 
				+ "-" + makeString(localDateTime.getDayOfMonth());
		
		return periodValue;
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.DAILY;
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
		
		if(year == null || month == null || day == null) {
			return false;
		}
	
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) && month.equals(localDateTime.getMonthValue()) && day.equals(localDateTime.getDayOfMonth())){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		DailyPeriod period = new DailyPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new DailyPeriod(new Date()));
	}

	@Override
	public Date beginsAt() {		
		return Date.from(
				LocalDateTime
					.of(
						getYear(), 
						getMonth(), 
						getDay(), 
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
						getMonth(), 
						getDay(), 
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
		return new DailyPeriod(
						Date.from(
								LocalDateTime
									.of(
										getYear(), 
										getMonth(), 
										getDay(), 
										0, 
										0, 
										0, 
										0
									)
									.plusDays(1)
									.atZone(ZoneId.systemDefault())
									.toInstant()
								)
							);	
	}

	@Override
	public Period previous() {
		return new DailyPeriod(
				Date.from(
						LocalDateTime
							.of(
								getYear(), 
								getMonth(), 
								getDay(), 
								0, 
								0, 
								0, 
								0
							)
							.minusDays(1)
							.atZone(ZoneId.systemDefault())
							.toInstant()
						)
					);	
	}

	@Override
	public Period move(Date date) {
		return new DailyPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {
		LocalDateTime currentPeriodStartsAt = LocalDateTime
							.of(
								getYear(), 
								getMonth(), 
								getDay(), 
								0, 
								0, 
								0, 
								0
							);
		
		return Duration.between(getStartMoment(), currentPeriodStartsAt).toDays();
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
