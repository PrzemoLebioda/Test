package com.comida.sia.sync.supervision.domain.model;

public abstract class Discriminator <T extends WaterMark>{
	protected String currentWaterMarkLevel;
	
	public Discriminator() {
		
	}
	
	public Discriminator(T currentWaterMark) {
		setCurrentWaterMarkLevel(currentWaterMark.calculateLevel());
	}
	
	public Discriminator(String currentWaterMarkLevel) {
		this.currentWaterMarkLevel = currentWaterMarkLevel;
	}
	
	public void setCurrentWaterMarkLevel(String waterMarkLevel) {
		this.currentWaterMarkLevel = waterMarkLevel;
	}
		
	public String getCurrentWaterMarkLevel() {
		return currentWaterMarkLevel;
	}
	
	public abstract Discriminator<T> alignTo(T waterMark) throws NotEligibleException;
	
	public abstract Boolean isEligible(T waterMark);
	
	public abstract Direction requiredOrder();
}
