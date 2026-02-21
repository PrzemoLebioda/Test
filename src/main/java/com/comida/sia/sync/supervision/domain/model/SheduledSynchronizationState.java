package com.comida.sia.sync.supervision.domain.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="SheduledSynchronizationState")
@DiscriminatorValue("SCHEDULED")
public class SheduledSynchronizationState extends SynchronizationState {

	public SheduledSynchronizationState() {
		super();
	}
	
	public SheduledSynchronizationState(UUID id, Direction direction, Period period, SynchronizationScope scope) {
		super(id,direction,period,scope);
	}
	
	@Override
	public void schedule() {
		Calendar schedule = GregorianCalendar.getInstance();
		schedule.setTime(getLastSyncedEventOccuranceTime());
		schedule.add(Calendar.DAY_OF_MONTH, 1);
		setNextSyncTime(schedule.getTime());
	}
	
}
