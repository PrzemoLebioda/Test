package com.comida.sia.fundamentals.port.application.cashflow;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public class SynchronizeCashFlowQuarterReportCommand extends Command<CashFlowsRegisterService> {

	private String tickerSymbol;
	private SynchronizationStateDto syncState;
	
	public SynchronizeCashFlowQuarterReportCommand(
			CashFlowsRegisterService requestClass,
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
		
		requestClass.synchronizeQuarterReports(tickerSymbol, syncState);
	}
	
}
