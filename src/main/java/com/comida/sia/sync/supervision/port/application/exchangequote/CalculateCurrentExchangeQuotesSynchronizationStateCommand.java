package com.comida.sia.sync.supervision.port.application.exchangequote;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CalculateCurrentExchangeQuotesSynchronizationStateCommand extends Command<ExchangeQuotesSynchronizationSupervisorService>{

	private String tickerSymbol;
	private Subject subject;
	private SynchronizationSummary summary;
	
	public CalculateCurrentExchangeQuotesSynchronizationStateCommand(
			ExchangeQuotesSynchronizationSupervisorService requestClass,
			String tickerSymbol,
			Subject subject,
			SynchronizationSummary summary) {
		
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setSummary(summary);
		setSubject(subject);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol must be provided in order to create the company data synchronized domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary is mandatory in order to create calculate current synchronization state command");
		this.summary = summary;
	}
	
	private void setSubject(Subject subject) {
		AssertionConcern.assertNotNull(summary, "Subject is mandatory in order to create calculate current synchronization state command");
		this.subject = subject;
	}

	@Override
	public void execute() {
		requestClass.calculateCurrentSyncState(
				subject,
				tickerSymbol,
				summary);
	}

}
