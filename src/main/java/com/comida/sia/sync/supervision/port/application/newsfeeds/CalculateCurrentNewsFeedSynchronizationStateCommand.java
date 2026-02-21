package com.comida.sia.sync.supervision.port.application.newsfeeds;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;

public class CalculateCurrentNewsFeedSynchronizationStateCommand extends Command<NewsFeedSynchronizationSupervisorService>{

	private SynchronizationSummary summary;
	
	public CalculateCurrentNewsFeedSynchronizationStateCommand(
			NewsFeedSynchronizationSupervisorService requestClass,
			SynchronizationSummary summary) {
		super(requestClass);
		setSummary(summary);
	}

	private void setSummary(SynchronizationSummary summary) {
		AssertionConcern.assertNotNull(summary, "Synchronization summary is mandatory in order to create calculate current synchronization state command");
		this.summary = summary;
	}
	
	@Override
	public void execute() {
		requestClass.calculateCurrentSyncState(summary);
	}

}
