package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.env.Environment;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;

@Entity(name="SynchronizationSupervisor")
@Table(	name = "synchronization_supervisors",
		indexes = {
				@Index(name = "sync_supervisors_ticker_symbol_idx", columnList = "ticker_symbol"),
				@Index(name = "sync_supervisors_sync_status_idx", columnList = "synchronization_status")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="supervisor_type", 
					 discriminatorType = DiscriminatorType.STRING)
public abstract class SynchronizationSupervisor {
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@Transient
	protected Notifier notifier;
	
	@Transient
	protected SubjectMapper subjectMapper;
	
	@Transient
	protected Environment env;
	 
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "synchronization_states_for_sync_supervisors", 
      joinColumns = {@JoinColumn(name = "supervisor_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "state_id", referencedColumnName = "id")})
    @MapKey(name = "scope")
	/*@OneToMany(mappedBy = "supervisor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@MapKey(name = "scope")*/
	private Map<SynchronizationScope, SynchronizationState> syncStateMap = new HashMap<>();
	
	public SynchronizationSupervisor() {

	}
	
	public SynchronizationSupervisor(UUID id) {
		setId(id);
	}
	
	private void setId(UUID id) {
		AssertionConcern.assertNotNull(id, "ID is mandatory in order to create synchronization supervisor");
		this.id = id;
	}
	
	protected abstract SubjectMapper getSubjectMapper();
	
	protected void register(SynchronizationState syncState) {
		AssertionConcern.assertNotNull(syncState.getScope(), "Scope of synchronization must be provided in order to register sync state");
		AssertionConcern.assertNotNull(syncState, "Synch state is obligatory for registration procedure");
		
		if(!syncStateMap.containsKey(syncState.getScope())) {
			syncStateMap.put(syncState.getScope(), syncState);
		}
	}
	
	public SynchronizationSupervisor with(Notifier notifier) {
		AssertionConcern.assertNotNull(notifier, "Notifier is madatory in order to notify about domain event");
		this.notifier = notifier;
		return this;
	}
	
	public SynchronizationSupervisor with(Environment env) {
		AssertionConcern.assertNotNull(env, "Environment is madatory in order to read properties");
		this.env = env;
		return this;
	}
	
	public abstract void orderSynchronization() throws InterruptedException, NumberFormatException, ParseException;
	
	protected Map<SynchronizationScope, SynchronizationState> getSyncStateMap() throws ParseException{	
		registerAllMissingSyncStates();
		return syncStateMap;
	}
	
	public abstract void registerAllMissingSyncStates() throws ParseException;
	
	public void updateSyncState(Subject subject, SynchronizationSummary summary) throws ParseException {
		AssertionConcern.assertNotNull(subject, "Subject is madatory in order to update synchronization state");
		
		getSyncStateMap().get(getSubjectMapper().getSyncStateTypeFrom(subject))
			.updateWith(summary);
	}
	
	public abstract void scheduleSynchronization(Date nextSyncDate) throws ParseException;
	
	protected <T extends SubjectedPayload> void publish(T domainEvent) {
		notifier.notify(domainEvent);
	}
	
}
