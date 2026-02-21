package com.comida.sia.sync.supervision.port.application.company;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CalculateCurrentCompanySynchronizationStateCommand extends Command<CompanySynchronizationSupervisorService> {
	
	private String tickerSymbol;
	private Subject subject;
	private SynchronizationSummary summary;
	
	public CalculateCurrentCompanySynchronizationStateCommand(
			CompanySynchronizationSupervisorService service,
			String tickerSymbol,
			Subject subject,
			SynchronizationSummary summary) {
		
		super(service);
		setTickerSymbol(tickerSymbol);
		setSummary(summary);
		setSubject(subject);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol must be provided in order to create the company data synchronized domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary is mandatory in order to create calculate current cynchronization state command");
		this.summary = summary;
	}
	
	private void setSubject(Subject subject) {
		AssertionConcern.assertNotNull(summary, "Subject is mandatory in order to create calculate current cynchronization state command");
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
