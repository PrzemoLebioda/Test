package com.comida.sia.sharedkernel.period;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;

@Embeddable
@DiscriminatorValue( "INFINITE_PERIOD" )
public class InfinitePeriod extends Period {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7482948847675598624L;
	
	public InfinitePeriod() {
		super("INFINITY");
	}
	
	@Override
	public PeriodType getType() {
		return PeriodType.INFINITY;
	}

	@Override
	public String getFormatted() {
		return periodValue;
	}

	@Override
	public Boolean isCurrent() {
		return Boolean.TRUE;
	}

	@Override
	public Boolean before(Period other) {
		return Boolean.FALSE;
	}

	@Override
	public Boolean after(Period other) {
		return Boolean.FALSE;
	}

	@Override
	public Boolean contains(Date date) {
		return Boolean.TRUE;
	}

	@Override
	public Boolean notContains(Date date) {
		return Boolean.FALSE;
	}

	@Override
	public Long distanceTo(Period other) {
		return Long.valueOf(0);
	}

	@Override
	public Long distanceToCurrentPeriod() {
		return Long.valueOf(0);
	}

	@Override
	public Date beginsAt() {
		return null;
	}

	@Override
	public Date endsAt() {
		return null;
	}

	@Override
	public Period next() {
		return this;
	}

	@Override
	public Period previous() {
		return this;
	}

	@Override
	public Period move(Date date) {
		return this;
	}

	@Override
	public Long getPeriodOrderNumber() {
		return Long.valueOf(1);
	}

}
