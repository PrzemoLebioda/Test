package com.comida.sia.sync.supervision.port.application.indicators;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public interface IndicatorsSynchronizationSupervisorService {
	public void orderSynchronization();
	public void calculateCurrentSyncState(Subject subject, SynchronizationSummary summary);
}
