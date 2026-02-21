package com.comida.sia.sync.supervision.port.application.exchangequote;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;

public class EstablishStartsynchronizationTimeCommand extends Command<InterdayExchangeQuotesSynchronizationSupervisorService>{

	private String tickerSymbol;
	private Date syncStartTime;
	
	public EstablishStartsynchronizationTimeCommand(
			InterdayExchangeQuotesSynchronizationSupervisorService requestClass,
			String tickerSymbol,
			Date syncStartTime) {
		
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setSyncStartTime(syncStartTime);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to establish synchronization time command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setSyncStartTime(Date syncStartTime) {
		this.syncStartTime = syncStartTime;
	}

	@Override
	public void execute() {
		requestClass.establishStartSynchronizationTime(tickerSymbol, syncStartTime);
	}

}
