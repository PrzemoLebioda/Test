package com.comida.sia.sync.supervision.port.application.calendar;

import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public interface CalendarSynchronizationSupervisorService {
	public void orderSynchronization();
	public void calculateCurrentSyncState(SynchronizationSummary summary);
}
