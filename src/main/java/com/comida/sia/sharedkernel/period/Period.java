package com.comida.sia.sharedkernel.period;

import java.time.LocalDateTime;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Embeddable;


@Embeddable
@DiscriminatorColumn( name = "period_type", discriminatorType = DiscriminatorType.STRING, columnDefinition = "varchar default 'INFINITE_PERIOD'", length = 32 )
public abstract class Period implements ValueObject<Period>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7692374330946598600L;
	
	@Column(name = "period_value", nullable = true)
	protected String periodValue;
	
	public Period() {
		
	}
	
	public Period(String period) {
		setPeriod(period);
	}

	private void setPeriod(String period) {
		TranslationConcern.assertNotEmpty(period, "Period value must be provided");
		this.periodValue = period;
	}
	
	public Boolean before(Period other) {
		if(other == null) {
			return null;
		}
		
		Long thisScore = this.getPeriodOrderNumber();
		Long otherScore = other.getPeriodOrderNumber();
		
		if(thisScore < otherScore) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean after(Period other) {
		if(other == null) {
			return null;
		}
		
		Long thisScore = this.getPeriodOrderNumber();
		Long otherScore = other.getPeriodOrderNumber();
		
		if(thisScore > otherScore) {
			return true;
		}else {
			return false;
		}
	}
	
	public Long distanceTo(Period other) {
		TranslationConcern.assertNotNull(other, "Distance calculation between periods requires other period");
		
		Long thisPeriodMonths = this.getPeriodOrderNumber();
		Long otherPeriodMonths = other.getPeriodOrderNumber();
		
		return thisPeriodMonths - otherPeriodMonths;
	}
	
	public Boolean notContains(Date date) {
		return !contains(date);
	}
	
	protected LocalDateTime getStartMoment() {
		return LocalDateTime
					.of(1990, 
						1, 
						1, 
						0, 
						0, 
						0, 
						0);
	}
	
	public abstract PeriodType getType();
	public abstract String getFormatted();
	public abstract Boolean isCurrent();

	public abstract Boolean contains(Date date);
	
	public abstract Long distanceToCurrentPeriod();
	
	public abstract Date beginsAt();
	public abstract Date endsAt();
	
	public abstract Period next();
	public abstract Period previous();
	public abstract Period move(Date date);
	
	public abstract Long getPeriodOrderNumber();
	
	@Override
	public boolean sameValueAs(Period other) {
		if(other != null) {
			return this.periodValue.equals(other.periodValue);
		}else {
			return false;
		}
	}

	protected static String makeString(int value) {
		String returnString = "";
		
		if(value < 10) {
			returnString = "0" + value;
		} else {
			returnString = returnString + value;
		}
		
		return returnString;
	}
	
	@Override
	public String toString() {
		return "Period [period=" + periodValue + "]";
	}
	
}
