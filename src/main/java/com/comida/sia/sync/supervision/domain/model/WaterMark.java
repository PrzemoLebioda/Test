package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;

import lombok.Getter;

public abstract class WaterMark implements Comparable<WaterMark>{
	@Getter private Direction order;
	
	public WaterMark(Direction sortingOrder) {
		setSortingOrder(sortingOrder);
	}
	
	private void setSortingOrder(Direction sortingOrder) {
		AssertionConcern.assertNotNull(sortingOrder, "Sorting order is mandatory in order to create water mark");
		this.order = sortingOrder;
	}
	
	public abstract Date occuranceTime() throws ParseException;
	public abstract String calculateLevel();
	
	public Boolean sameLevel(WaterMark otherWaterMark) {
		if(calculateLevel() == null && (otherWaterMark == null || otherWaterMark.calculateLevel() == null || otherWaterMark.calculateLevel().isEmpty())) {
			return true;
		}
		
		if(calculateLevel().compareTo(otherWaterMark.calculateLevel()) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean sameLevel(String otherWaterMarkLevel) {
		if(calculateLevel() == null && (otherWaterMarkLevel == null || otherWaterMarkLevel.isEmpty())) {
			return true;
		}
		
		if(calculateLevel().compareTo(otherWaterMarkLevel) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isAbove(WaterMark otherWaterMark) {
		if(calculateLevel().compareTo(otherWaterMark.calculateLevel()) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isAbove(String otherWaterMarkLevel) {
		if(calculateLevel().compareTo(otherWaterMarkLevel) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isBelow(WaterMark otherWaterMark) {
		if(calculateLevel().compareTo(otherWaterMark.calculateLevel()) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isBelow(String otherWaterMarkLevel) {
		if(calculateLevel().compareTo(otherWaterMarkLevel) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(WaterMark o) {
		switch (order) {
			case ASCENDING:
				return calculateLevel().compareTo(o.calculateLevel());
			case DESCENDING:
				return calculateLevel().compareTo(o.calculateLevel()) * -1;
			default:
				throw new IllegalArgumentException("Sorting order is not specified.");
		}
	}

	@Override
	public String toString() {
		return "WaterMark [order=" + order + "]";
	}
	
}
