package com.comida.sia.sync.supervision.domain.model.calendar;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SheduledSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSupervisor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="CalendarSynchronizationSupervisor")
@DiscriminatorValue("CALENDAR")
public class CalendarSynchronizationSupervisor extends SynchronizationSupervisor {

	
	public CalendarSynchronizationSupervisor() {
		super();
	}
	
	public CalendarSynchronizationSupervisor(UUID id) {
		super(id);
	}
	
	@Override
	public void registerAllMissingSyncStates() {
		registerEarningsCalendarSyncState();
		
	}
	
	private void registerEarningsCalendarSyncState() {
		register(new SheduledSynchronizationState(
				UUID.randomUUID(), 
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.EARNINGS_CALENDAR
			)
		);
	}

	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		getSyncStateMap().get(SynchronizationScope.EARNINGS_CALENDAR).schedule(nextSyncDate);		
	}

	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new CalendarSubjectMapper();
		}
		
		return subjectMapper;
	}

	@Override
	public void orderSynchronization() throws NumberFormatException, InterruptedException, ParseException {		
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new CalendarSyncDomainEventAnnouncer(notifier, syncState)) // 
				.synchronize();
		}
	}

}
