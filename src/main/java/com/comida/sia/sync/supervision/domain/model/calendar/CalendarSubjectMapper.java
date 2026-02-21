package com.comida.sia.sync.supervision.domain.model.calendar;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class CalendarSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case CALENDAR_EARNINGS_SYNC_ORDERED: 
				return SynchronizationScope.EARNINGS_CALENDAR;
			case CALENDAR_EARNINGS_SYNCED:
				return SynchronizationScope.EARNINGS_CALENDAR;
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}

	}

}
