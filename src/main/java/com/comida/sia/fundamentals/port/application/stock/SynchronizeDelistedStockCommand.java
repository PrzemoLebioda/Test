package com.comida.sia.fundamentals.port.application.stock;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class SynchronizeDelistedStockCommand extends Command<StockService> {

	private SynchronizationStateDto syncState;
	
	public SynchronizeDelistedStockCommand(StockService requestClass, SynchronizationStateDto syncState) {
		super(requestClass);
		setSynchronizationState(syncState);
	}
	
	private void setSynchronizationState(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		this.syncState = syncState;
	}

	@Override
	public void execute() {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		requestClass.synchronizeDelistedStock(syncState);
	}
}
