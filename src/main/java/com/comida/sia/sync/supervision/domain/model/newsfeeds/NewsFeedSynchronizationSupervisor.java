package com.comida.sia.sync.supervision.domain.model.newsfeeds;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.period.InfinitePeriod;
import com.comida.sia.sync.supervision.domain.model.ContinousSynchronizationState;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationState;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSupervisor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="NewsFeedSynchronizationSupervisor")
@DiscriminatorValue("NEWS_FEED")
public class NewsFeedSynchronizationSupervisor extends SynchronizationSupervisor{

	public NewsFeedSynchronizationSupervisor() {
		super();
	}
	
	public NewsFeedSynchronizationSupervisor(UUID id) {
		super(id);
	}

	@Override
	protected SubjectMapper getSubjectMapper() {
		if(subjectMapper == null) {
			subjectMapper = new NewsFeedSubjectMapper();
		}
		
		return subjectMapper;
	}
	
	@Override
	public void scheduleSynchronization(Date nextSyncDate) throws ParseException {
		AssertionConcern.assertNotNull(getSyncStateMap(), "Synchronization states map must not be empty");
		getSyncStateMap().get(SynchronizationScope.NEWS_FEED).schedule(nextSyncDate);
	}

	@Override
	public void orderSynchronization() throws NumberFormatException, InterruptedException, ParseException {
		for(SynchronizationState syncState : getSyncStateMap().values()) {
			Thread.sleep(Long.parseLong(env.getProperty("interval")));
			syncState
				.with(new NewsFeedSyncDomainEventAnnouncer(notifier, syncState))
				.synchronize();
		}
	}

	@Override
	public void updateSyncState(Subject subject, SynchronizationSummary summary) throws ParseException {
		AssertionConcern.assertNotNull(subject, "Subject is madatory in order to update synchronization state");
		SynchronizationState syncState = getSyncStateMap().get(getSubjectMapper().getSyncStateTypeFrom(subject));
		
		syncState
			.with(new NewsFeedSyncDomainEventAnnouncer(notifier, syncState))
			.updateWith(summary);
	}
	
	@Override
	public void registerAllMissingSyncStates() {
		registerNewsFeedSyncState();
	}
	
	private void registerNewsFeedSyncState() {
		register(new ContinousSynchronizationState(
				UUID.randomUUID(),
				Direction.ASCENDING, 
				new InfinitePeriod(), 
				SynchronizationScope.NEWS_FEED
			)
		);
	}
}
