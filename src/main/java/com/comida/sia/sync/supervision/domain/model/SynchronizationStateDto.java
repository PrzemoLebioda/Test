package com.comida.sia.sync.supervision.domain.model;

import java.util.Date;
import java.util.UUID;

import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sharedkernel.period.PeriodType;

import lombok.Getter;

public class SynchronizationStateDto {
	@Getter private UUID id;
	@Getter private SynchronizationScope scope;
	@Getter private Period period;
	@Getter private Direction direction;
	@Getter private String waterMarkCurrentLevel;
	@Getter private Date lastSyncTime;
	@Getter private Date lastSyncedEventOccuranceTime;
	@Getter private Date nextSyncTime;
	@Getter private Date startSyncTime; 
	
	public SynchronizationStateDto(SynchronizationState syncState){
		id = syncState.getId();
		scope = syncState.getScope();
		period = syncState.getPeriod();
		direction = syncState.getDirection();
		waterMarkCurrentLevel = syncState.getWaterMarkCurrentLevel();
		lastSyncTime = syncState.getLastSyncTime();
		lastSyncedEventOccuranceTime = syncState.getLastSyncedEventOccuranceTime();
		nextSyncTime = syncState.getNextSyncTime();
		startSyncTime = syncState.getStartSyncTime();
	}
	
	public SynchronizationStateDto(SynchronizationScope scope, Direction direction, Date lastSyncedEventOccuranceTime, String waterMarkCurrentLevel) {
		this.scope = scope;
		this.direction = direction;
		this.lastSyncedEventOccuranceTime = lastSyncedEventOccuranceTime;
		this.waterMarkCurrentLevel = waterMarkCurrentLevel;
	}
	
	public PeriodType getPeriodType() {
		return period.getType();
	}

	@Override
	public String toString() {
		return "SynchronizationStateDto [id=" + id + ", scope=" + scope + ", period=" + period + ", direction="
				+ direction + ", waterMarkCurrentLevel=" + waterMarkCurrentLevel + ", lastSyncTime=" + lastSyncTime
				+ ", lastSyncedEventOccuranceTime=" + lastSyncedEventOccuranceTime + ", nextSyncTime=" + nextSyncTime
				+ ", startSyncTime=" + startSyncTime + "]";
	}

}
