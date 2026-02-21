package com.comida.sia.sharedkernel.period;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

import com.comida.sia.sharedkernel.AssertionConcern;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "WEEKLY_PERIOD" )
public class WeeklyPeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1908880223892082136L;

	public WeeklyPeriod() {
		super();
	}
	
	public WeeklyPeriod(Date date) {
		super(calculatePeriodValueFrom(date));
	}
	
	private static String calculatePeriodValueFrom(Date date) {
		AssertionConcern.assertNotNull(date, "Date can't be null when period is determined");
		
		String periodValue = "";		
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
		periodValue = periodValue 
				+ localDateTime.getYear()
				//+ "-" + makeString(localDateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
				+ "-" + makeString(localDateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
		return periodValue;
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.WEEKLY;
	}

	@Override
	public String getFormatted() {
		return periodValue;
	}

	@Override
	public Boolean isCurrent() {
		Integer year = getYear();
		Integer week = getWeek();

		
		if(year == null || week == null) {
			return false;
		}
	
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if(year.equals(localDateTime.getYear()) && week.equals(localDateTime.get(WeekFields.of(Locale.getDefault()).weekOfYear()))){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean contains(Date date) {
		WeeklyPeriod period = new WeeklyPeriod(date);
		return this.sameValueAs(period);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return distanceTo(new WeeklyPeriod(new Date()));
	}

	@Override
	public Date beginsAt() {
		LocalDate firstDayOfGivenWeek = firstDayOfWeek(getYear(), getWeek()).toLocalDate();
		
		return Date.from(
				LocalDateTime
					.of(
						firstDayOfGivenWeek.getYear(), 
						firstDayOfGivenWeek.getMonthValue(), 
						firstDayOfGivenWeek.getDayOfMonth(), 
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
		LocalDate lastDayOfGivenWeek = firstDayOfWeek(getYear(), getWeek()).plusDays(6).toLocalDate();
		
		return Date.from(
				LocalDateTime
					.of(
						lastDayOfGivenWeek.getYear(), 
						lastDayOfGivenWeek.getMonthValue(), 
						lastDayOfGivenWeek.getDayOfMonth(), 
						23, 
						59, 
						59, 
						999999999)
					.atZone(ZoneId.systemDefault())
					.toInstant()
			);
	}

	private LocalDateTime firstDayOfWeek(int year, int week) {
		return LocalDateTime
	        		.of(year, 1, 1, 0, 0, 0)
	        		.with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek())
	        		.with(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(), week);
	}
	
	@Override
	public Period next() {
		LocalDate firstDayOfGivenWeek = firstDayOfWeek(getYear(), getWeek()).plusWeeks(1).toLocalDate();
		
		return new WeeklyPeriod(
				Date.from(
						LocalDateTime
							.of(
								firstDayOfGivenWeek.getYear(), 
								firstDayOfGivenWeek.getMonthValue(), 
								firstDayOfGivenWeek.getDayOfMonth(), 
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
		LocalDate firstDayOfGivenWeek = firstDayOfWeek(getYear(), getWeek()).minusWeeks(1).toLocalDate();
		
		return new WeeklyPeriod(
				Date.from(
						LocalDateTime
							.of(
								firstDayOfGivenWeek.getYear(), 
								firstDayOfGivenWeek.getMonthValue(), 
								firstDayOfGivenWeek.getDayOfMonth(), 
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
	public Period move(Date date) {
		return new WeeklyPeriod(date);
	}

	@Override
	public Long getPeriodOrderNumber() {
		return Duration.between(getStartMoment(), firstDayOfWeek(getYear(), getWeek())).toDays()/7;
	}

	private Integer getYear() {
		return Integer.valueOf(getFormatted().substring(0, 4));
	}
	
	private Integer getWeek() {
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
