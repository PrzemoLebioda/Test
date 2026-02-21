package com.comida.sia.sync.supervision.domain.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.period.Period;

import lombok.Getter;

public class SynchronizationReport <T extends WaterMark, R extends TimeSeries> {
	@Getter private String tickerSymbol;
	@Getter private Period period;
	@Getter private String waterMarkLevel;
	
	@Getter private Date created;
	@Getter private Date issued;
	private List<R> synchronizedItems;
	private List<T> alreadySynchronizedItems;
	private List<T> outOfPeriodItems;
	
	public SynchronizationReport() {
		setCreated();
	}
	
	private void setCreated() {
		created = new Date();
	}
	
	public void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public void setPeriod(Period period) {
		AssertionConcern.assertNotNull(period, "Period is mandatory");
		this.period = period;
	}
	
	public void setWaterMarkLevel(String waterMarkLevel) {
		this.waterMarkLevel = waterMarkLevel;
	}
	
	public void addSynchronizedItem(R item) {
		//assertNotNull(item, "Item can't be null in order to include it to synchronized items list");
		if(item != null) {
			getSynchronizedItems().add(item);
		}
	}
	
	public List<R> obtainSynchronizedItems(){
		return Collections.unmodifiableList(getSynchronizedItems());
	}
	
	private List<R> getSynchronizedItems(){
		if(synchronizedItems == null) {
			synchronizedItems = new ArrayList<>();
		}
		
		return synchronizedItems;
	}
	
	public void addOutOfPeriodItem(T item) {
		AssertionConcern.assertNotNull(item, "Item can't be null in order to include it to out of period items list");
		if(item != null) {
			getOutOfPeriodItems().add(item);
		}
	}
	
	public List<T> obtainOutOfPeriodItems(){
		return Collections.unmodifiableList(getOutOfPeriodItems());
	}
	
	private List<T> getOutOfPeriodItems(){
		if(outOfPeriodItems == null) {
			outOfPeriodItems = new ArrayList<>();
		}
		
		return outOfPeriodItems;
	}
	
	public void addAlreadySynchronizedItem(T item) {
		//assertNotNull(item, "Item can't be null in order to include it to out of period items list");
		if(item != null) {
			getAlreadySynchronizedItems().add(item);
		}
		
	}
	
	public List<T> obtainAlreadySynchronizedItems(){
		return Collections.unmodifiableList(getAlreadySynchronizedItems());
	}
	
	private List<T> getAlreadySynchronizedItems(){
		if(alreadySynchronizedItems == null) {
			alreadySynchronizedItems = new ArrayList<>();
		}
		
		return alreadySynchronizedItems;
	}
	
	public SynchronizationReport<T, R> issue() {
		issued = new Date();
		
		return this;
	}
	
	public Date getFirstSynchronizedItemOccuranceTime() {
		if(getSynchronizedItems().size() > 0) {
			return getSynchronizedItems().get(0).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Date getLastSynchronizedItemOccuranceTime() {
		int itemsCount = getSynchronizedItems().size();
		if(itemsCount > 0) {
			return getSynchronizedItems().get(itemsCount - 1).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Date getFirstOutOfPeriodItemOccuranceTime() throws ParseException {
		if(getOutOfPeriodItems().size() > 0) {
			return getOutOfPeriodItems().get(0).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Date getLastOutOfPeriodItemOccuranceTime() throws ParseException {
		int itemsCount = getOutOfPeriodItems().size();
		if(itemsCount > 0) {
			return getOutOfPeriodItems().get(itemsCount - 1).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Date getAlreadySynchronizedItemOccuranceTime() throws ParseException {
		if(getAlreadySynchronizedItems().size() > 0) {
			return getAlreadySynchronizedItems().get(0).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Date getLastAlreadySynchronizedItemOccuranceTime() throws ParseException {
		int itemsCount = getAlreadySynchronizedItems().size();
		if(itemsCount > 0) {
			return getAlreadySynchronizedItems().get(itemsCount - 1).occuranceTime();
		} else {
			return null;
		}
	}
	
	public Boolean containsOutOfPeriodItems() {
		return negate(getOutOfPeriodItems().isEmpty());
	}
	
	private Boolean negate(Boolean boolVal) {
		return !boolVal;
	}
	
	public SynchronizationSummary getSummary() {
		return new SynchronizationSummary(
				period,
				waterMarkLevel,
				issued,
				getLastSynchronizedItemOccuranceTime());
	}

	@Override
	public String toString() {
		return "SynchronizationReport [tickerSymbol=" + tickerSymbol + ", period=" + period
				+ ", created=" + created + ", issued=" + issued + ", synchronizedItems=" + synchronizedItems
				+ ", alreadySynchronizedItems=" + alreadySynchronizedItems + ", outOfPeriodItems=" + outOfPeriodItems
				+ "]";
	}
}
