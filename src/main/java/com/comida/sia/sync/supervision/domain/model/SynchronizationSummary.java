package com.comida.sia.sync.supervision.domain.model;

import java.util.Date;
import java.util.Objects;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sharedkernel.period.Period;

import lombok.Getter;

@Getter
public class SynchronizationSummary implements ValueObject<SynchronizationSummary> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4885142873951575179L;

	private Period period;
	private String waterMarkLevel;
	private Date lastSyncTime;
	private Date lastSyncedEventOccuranceTime;
	
	public SynchronizationSummary() {
		super();
	}
	
	public SynchronizationSummary(
			Period period, 
			String waterMarkLevel, 
			Date lastSyncTime, 
			Date lastSyncedEventOccuranceTime) {
		
		setPeriod(period);
		setWaterMarkLevel(waterMarkLevel);
		setLastSycTime(lastSyncTime);
		setLastSyncedEventOccuranceTime(lastSyncedEventOccuranceTime);
	}
	
	private void setPeriod(Period period) {
		AssertionConcern.assertNotNull(period, "Period is mandatory in order to create synchronization report summary");
		this.period = period;
	}
	
	private void setWaterMarkLevel(String waterMarkLevel) {
		AssertionConcern.assertNotEmpty(waterMarkLevel, "Water mark level is mandatory in order to create synchronization report summary");
		this.waterMarkLevel = waterMarkLevel;
	}
	
	private void setLastSycTime(Date lastSyncTime) {
		AssertionConcern.assertNotNull(lastSyncTime, "Last sync time is mandatory in order to create synchronization report summary");
		this.lastSyncTime = lastSyncTime;
	}
	
	private void setLastSyncedEventOccuranceTime(Date lastSyncedEventOccuranceTime) {
		AssertionConcern.assertNotNull(lastSyncTime, "Last synced event occurance time is mandatory in order to create synchronization report summary");
		this.lastSyncedEventOccuranceTime = lastSyncedEventOccuranceTime;
	}

	@Override
	public boolean sameValueAs(SynchronizationSummary other) {
		if(other != null) {
			return  ComparationConcern.sameTexts(this.waterMarkLevel, other.waterMarkLevel) &&
					ComparationConcern.sameDates(this.lastSyncTime, other.lastSyncTime) &&
					ComparationConcern.sameDates(this.lastSyncedEventOccuranceTime, other.lastSyncedEventOccuranceTime) &&
					this.period.equals(other.period);
			
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastSyncTime, lastSyncedEventOccuranceTime, period, waterMarkLevel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SynchronizationSummary other = (SynchronizationSummary) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "SynchronizationReportSymmary [period=" + period + ", waterMarkLevel="
				+ waterMarkLevel + ", lastSyncTime=" + lastSyncTime + ", lastSyncedEventOccuranceTime="
				+ lastSyncedEventOccuranceTime + "]";
	}
}
