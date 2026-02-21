package com.comida.sia.sync.supervision.domain.model;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;

public class ImmediateScheduler implements Scheduler{

	public Notifier notifier;
	//tutaj też by się przydał jakiś domain event builder
	
	public ImmediateScheduler(Notifier notifier) {
		setNofifier(notifier);
	}
	
	private void setNofifier(Notifier notifier) {
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to create immediate scheduler");
		this.notifier = notifier;
	}
	
	@Override
	public void schedule(SynchronizationState syncState) {	
		Date toDay = new Date();
		
		if(ComparationConcern.differentDates(toDay, syncState.getNextSyncTime())) {
			syncState.setNextSyncTime(toDay);
			notifier.notify(null);
		}
	}
	
	

}
