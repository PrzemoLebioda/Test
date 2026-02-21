package com.comida.sia.indicators.port.application.treasuryyeald;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class SynchronizeTreasuryYealdWeekly02YearCommand  extends Command<TreasuryYealdService>{

	private SynchronizationStateDto syncState;
	
	public SynchronizeTreasuryYealdWeekly02YearCommand(TreasuryYealdService requestClass, SynchronizationStateDto syncState) {
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
		requestClass.synchronizeTreasuryYealdWeekly02Year(syncState);
	}

}
