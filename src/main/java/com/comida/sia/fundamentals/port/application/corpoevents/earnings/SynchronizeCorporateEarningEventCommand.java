package com.comida.sia.fundamentals.port.application.corpoevents.earnings;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class SynchronizeCorporateEarningEventCommand extends Command<EarningsCalendarService>{

	private SynchronizationStateDto syncState;
	
	public SynchronizeCorporateEarningEventCommand(
			EarningsCalendarService requestClass,
			SynchronizationStateDto syncState) {
		
		super(requestClass);
		setSyncState(syncState);
	}
	
	private void setSyncState(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		this.syncState = syncState;
	}

	@Override
	public void execute() {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		
		requestClass.synchronizeEvents(syncState);
	}

}
