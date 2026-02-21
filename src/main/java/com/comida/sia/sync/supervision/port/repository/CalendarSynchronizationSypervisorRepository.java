package com.comida.sia.sync.supervision.port.repository;

import com.comida.sia.sync.supervision.domain.model.calendar.CalendarSynchronizationSupervisor;

public interface CalendarSynchronizationSypervisorRepository {
	void store(CalendarSynchronizationSupervisor supervisor);
	void update(CalendarSynchronizationSupervisor supervisor);
	
	CalendarSynchronizationSupervisor get();
}
