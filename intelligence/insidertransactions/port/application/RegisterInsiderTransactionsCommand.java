package com.comida.sia.intelligence.insidertransactions.port.application;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class RegisterInsiderTransactionsCommand extends Command<InsiderTransactionService>{

	private String tickerSymbol;
	private SynchronizationStateDto syncState;
	
	public RegisterInsiderTransactionsCommand(
			InsiderTransactionService requestClass,
			String tickerSymbol,
			SynchronizationStateDto syncState) {
		
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setSyncState(syncState);
	}

	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to execute the command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setSyncState(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		this.syncState = syncState;
	}

	@Override
	public void execute() {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to execute the command");
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		
		requestClass.registerInsiderTransactionsFor(tickerSymbol, syncState);
	}
}
