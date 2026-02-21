package com.comida.sia.sync.supervision.domain.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.period.Period;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="ContinousSynchronizationState")
@DiscriminatorValue("CONTINOUS")
public class ContinousSynchronizationState extends SynchronizationState{
	
	public ContinousSynchronizationState() {
		super();
	}
	
	public ContinousSynchronizationState(UUID id, Direction direction, Period period, SynchronizationScope scope) {
		super(id,direction,period,scope);
	}
	
	@Override
	public void schedule() {
		Calendar schedule = GregorianCalendar.getInstance();
		schedule.setTime(getLastSyncedEventOccuranceTime());
		schedule.add(Calendar.DAY_OF_MONTH, 1);
		setNextSyncTime(schedule.getTime());

		Date todayAtMidnight = TranslationConcern.getMidnightAt(new Date());
		
		if(getNextSyncTime().before(todayAtMidnight)) {
			synchronize();
		}
	}	
}
