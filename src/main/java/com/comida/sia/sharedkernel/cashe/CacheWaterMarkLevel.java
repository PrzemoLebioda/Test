package com.comida.sia.sharedkernel.cashe;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

public class CacheWaterMarkLevel extends WaterMark {

	private String waterMarkLevel;
	private Date occuranceTime;
	
	public CacheWaterMarkLevel(WaterMark waterMark) throws ParseException {
		super(waterMark.getOrder());
		waterMarkLevel = waterMark.calculateLevel();
		occuranceTime = waterMark.occuranceTime();
	}
	
	public CacheWaterMarkLevel(SynchronizationStateDto syncState) {
		super(syncState.getDirection());
		this.waterMarkLevel = syncState.getWaterMarkCurrentLevel();
		this.occuranceTime = syncState.getLastSyncedEventOccuranceTime();
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return occuranceTime;
	}

	@Override
	public String calculateLevel() {
		return waterMarkLevel;
	}

	@Override
	public String toString() {
		return "CacheWaterMarkLevel [waterMarkLevel=" + waterMarkLevel + ", occuranceTime=" + occuranceTime
				+ ", getOrder()=" + getOrder() + "]";
	}
	
}
