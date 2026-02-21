package com.comida.sia.exchangequote.port.application;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class SynchronizeInterday01MinQuotationsCommand extends Command<InterdayExchangeQuoteService>{
	private String tickerSymbol;
	private SynchronizationStateDto syncState;
	
	public SynchronizeInterday01MinQuotationsCommand(
				InterdayExchangeQuoteService requestClass,
				String tickerSymbol,
				SynchronizationStateDto syncState) {
		
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setPeriod(syncState);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, SynchronizeInterday01MinQuotationsCommand.class.getSimpleName() + ": Ticker symbol is mandatory in order to create command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setPeriod(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, SynchronizeInterday01MinQuotationsCommand.class.getSimpleName() + ": Sync state is mandatory in order to create command");
		this.syncState = syncState;
	}

	@Override
	public void execute() {
		AssertionConcern.assertNotEmpty(tickerSymbol, SynchronizeInterday01MinQuotationsCommand.class.getSimpleName() + ": Ticker symbol is mandatory in order to execute command");
		AssertionConcern.assertNotNull(syncState, SynchronizeInterday01MinQuotationsCommand.class.getSimpleName() + ": sync state is mandatory in order to execute command");
		
		requestClass.synchronizeInterday01MinQuotations(tickerSymbol, syncState);
	}
}
