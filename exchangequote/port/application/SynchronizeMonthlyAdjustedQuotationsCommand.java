package com.comida.sia.exchangequote.port.application;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

public class SynchronizeMonthlyAdjustedQuotationsCommand extends Command<AdjustedExchangeQuoteService>{
	
	private String tickerSymbol;
	private SynchronizationStateDto syncState;
	
	public SynchronizeMonthlyAdjustedQuotationsCommand(
				AdjustedExchangeQuoteService requestClass,
				String tickerSymbol,
				SynchronizationStateDto syncState) {
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setSyncState(syncState);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, SynchronizeMonthlyAdjustedQuotationsCommand.class.getSimpleName() + ": Ticker symbol is mandatory in order to create command");
		this.tickerSymbol = tickerSymbol;
	}

	private void setSyncState(SynchronizationStateDto syncState) {
		AssertionConcern.assertNotNull(syncState, "Current synchronization state is mandatory in order to execute command");
		this.syncState = syncState;
	}
	
	@Override
	public void execute() throws ParseException, IOException, CsvException {
		AssertionConcern.assertNotEmpty(tickerSymbol, SynchronizeMonthlyAdjustedQuotationsCommand.class.getSimpleName() + ": Ticker symbol is mandatory in order to execute command");
		requestClass.synchronizeMonthlyAdjustedQuotations(tickerSymbol, syncState);
	}
}
