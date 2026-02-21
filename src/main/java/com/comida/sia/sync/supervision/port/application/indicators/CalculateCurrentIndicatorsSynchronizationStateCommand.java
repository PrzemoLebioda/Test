package com.comida.sia.sync.supervision.port.application.indicators;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CalculateCurrentIndicatorsSynchronizationStateCommand extends Command<IndicatorsSynchronizationSupervisorService> {

	private SynchronizationSummary summary;
	private Subject subject;
	
	public CalculateCurrentIndicatorsSynchronizationStateCommand(
			IndicatorsSynchronizationSupervisorService requestClass,
			Subject subject,
			SynchronizationSummary summary) {
		
		super(requestClass);
		setSummary(summary);
		setSubject(subject);
	}
	
	private void setSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary is mandatory in order to create calculate current synchronization state command");
		this.summary = summary;
	}
	
	private void setSubject(Subject subject) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary is mandatory in order to create calculate current synchronization state command");
		this.subject = subject;
	}

	@Override
	public void execute() {
		requestClass.calculateCurrentSyncState(subject, summary);
	}

}
