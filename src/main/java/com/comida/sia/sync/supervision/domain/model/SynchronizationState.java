package com.comida.sia.sync.supervision.domain.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sharedkernel.period.PeriodType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;

@Entity(name = "SynchronizationState")
@Table(name = "synchronization_states")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="sync_state_type", 
					 discriminatorType = DiscriminatorType.STRING)
public abstract class SynchronizationState{
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
		
	@Column(name = "scope", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private SynchronizationScope scope;
	
	@Embedded
	@Getter private Period period;
	
	@Column(name = "direction", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private Direction direction;
	
	@Column(name = "water_mark_current_level", nullable = true)
	@Getter private String waterMarkCurrentLevel;
	
	@Column(name = "last_sync_time", nullable = true)
	@Getter private Date lastSyncTime;
	
	@Column(name = "last_synced_event_occurance_time", nullable = true)
	@Getter private Date lastSyncedEventOccuranceTime;
	
	@Column(name = "next_sync_time", nullable = true)
	@Getter private Date nextSyncTime;
	
	@Column(name = "start_sync_time", nullable = true)
	@Getter private Date startSyncTime;
	
	@Transient
	protected DomainEventAnnouncer eventAnnouncer;
	
	public SynchronizationState() {
		
	}
	
	public SynchronizationState(UUID id, Direction direction, Period period, SynchronizationScope scope) {
		setId(id);
		setDirection(direction);
		setPeriod(period);
		setScope(scope);
	}
	
	private void setId(UUID id) {
		this.id = id;
	}
	
	private void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	private void setScope(SynchronizationScope scope) {
		AssertionConcern.assertNotNull(scope, "Scope must be provided in order to create scoped synch state");
		this.scope = scope;
	}
	
	protected void setNextSyncTime(Date nextSyncTime) {
		this.nextSyncTime = nextSyncTime;
	}
	
	private Boolean canSynchronize() {
		if(nextSyncTime == null) {
			return true;
		}
		
		switch(direction) {
			case ASCENDING:
				return canSynchrnizeInAscOrder();
			case DESCENDING:
				return canSynchronizeInDescOrder();
			default:
				return true;
		}
	}
	
	private Boolean canSynchrnizeInAscOrder() {
		Date now = new Date();
		
		if(nextSyncTime.before(now)) {
			return true;
		} else {
			return false;
		}
	}
	
	private Boolean canSynchronizeInDescOrder() {
		Date now = new Date();

		if(now.after(nextSyncTime)) {
			return false;
		} else {
			return true;
		}
	}
	
	public SynchronizationState with(SyncDomainEventAnnouncer eventAnnouncer) {
		this.eventAnnouncer = eventAnnouncer;
		return this;
	}
	
	public void synchronize() {
		if(canSynchronize()) {
			AssertionConcern.assertNotNull(eventAnnouncer, "Domain event announcer is necessary for annouce synchronization order");
			
			eventAnnouncer.announce(); 
		}
	}
	
	public void updateWith(SynchronizationSummary summary) {
		setPeriod(summary.getPeriod());
		setWaterMarkCurrentLevel(summary.getWaterMarkLevel());
		setLastSyncTime(summary.getLastSyncTime());
		setLastSyncedEventOccuranceTime(summary.getLastSyncedEventOccuranceTime());
		
		schedule();
	}
	
	private void setPeriod(Period period) {
		AssertionConcern.assertNotNull(period, "Period must be provided");
		this.period = period;
	}
	
	private void setWaterMarkCurrentLevel(String waterMarkCurrentLevel) {
		AssertionConcern.assertNotEmpty(waterMarkCurrentLevel, "Current water mark level must be provided");
		this.waterMarkCurrentLevel = waterMarkCurrentLevel;
	}
	
	private void setLastSyncTime(Date lastSyncTime) {
		AssertionConcern.assertNotNull(lastSyncTime, "Last synchronization time must be provided");
		this.lastSyncTime = lastSyncTime;
	}
	
	private void setLastSyncedEventOccuranceTime(Date lastSyncedEventOccuranceTime) {
		AssertionConcern.assertNotNull(lastSyncedEventOccuranceTime, "Last synchronized event occurance time must be provided");
		this.lastSyncedEventOccuranceTime = lastSyncedEventOccuranceTime;
		
	}
	
	public abstract void schedule();
	
	public void schedule(Date nextSyncDate) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(nextSyncDate.toInstant(), ZoneId.systemDefault());
		
		this.nextSyncTime = Date.from(
				localDateTime
					.plusDays(1)
					.atZone(ZoneId.systemDefault())
					.toInstant()
				);
	}
	
	public PeriodType getPeriodType() {
		return period.getType();
	}
		
	public String getClassName() {
		 return this.getClass().getName();
	}

	public void setStartSyncTime(Date startSyncTime) {
		this.startSyncTime = ComparationConcern.getMin(this.startSyncTime, startSyncTime);
	}
	
	@Override
	public String toString() {
		return "SynchronizationState [id=" + id + ", period=" + period + ", direction=" + direction
				+ ", waterMarkCurrentLevel=" + waterMarkCurrentLevel + ", lastSyncTime=" + lastSyncTime
				+ ", lastSyncedEventOccuranceTime=" + lastSyncedEventOccuranceTime + ", nextSyncTime=" + nextSyncTime
				+ "]";
	}
}
