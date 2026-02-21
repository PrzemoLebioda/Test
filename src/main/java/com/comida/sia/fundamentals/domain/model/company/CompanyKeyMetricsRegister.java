package com.comida.sia.fundamentals.domain.model.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.domain.DomainEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "CompanyKeyMetricsRegister")
@Table(name = "companies_key_metrics_registers")
public class CompanyKeyMetricsRegister implements DomainEntity<CompanyKeyMetricsRegister>{
	
	@Id
	@Column(name = "ticker_symbol", nullable = false)
	@Getter private String tickerSymbol;
	
	@Column(name = "last_modified_time", nullable = true)
	@Getter private Date lastModifiedTime;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)   
	@JoinColumn(name = "ticker_symbol")
	private List<CompanyKeyMetrics> keyMetricsList;
	
	public CompanyKeyMetricsRegister() {
		super();
	}
	
	public CompanyKeyMetricsRegister(String tickerSymbol) {
		this.setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Key metrics register: ticker symbol can't be empty or null");
		this.tickerSymbol = tickerSymbol;
	}
	
	private List<CompanyKeyMetrics> getKeyMetricsList(){
		if(keyMetricsList == null) {
			this.keyMetricsList = new ArrayList<CompanyKeyMetrics>();
		}
		
		return this.keyMetricsList;
	}
	
	public List<CompanyKeyMetrics> getKeyMetrics(){
		return Collections.unmodifiableList(this.getKeyMetricsList());
	}
	
	public CompanyKeyMetrics getRecent() {
		Collections.sort(this.getKeyMetricsList());
		if((this.getKeyMetricsList().size() == 0) || (this.getKeyMetricsList().get(0) == null)) {
			return null;
		} else {
			return this.getKeyMetricsList().get(0);
		}
		
	}
	
	public void registerKeyMetrics(CompanyKeyMetrics keyMetrics) throws CloneNotSupportedException {		
		AssertionConcern.assertNotNull(keyMetrics, "Key metrics can't be null");
		AssertionConcern.assertNotEquals(keyMetrics, this.getRecent(), "In order to register company's updated key metrics the new one must be different then current one");
		
		this.keyMetricsList.add(keyMetrics);
		this.lastModifiedTime = new Date();	
	}

	@Override
	public boolean sameIdentityAs(CompanyKeyMetricsRegister other) {
		if(other == null) {
			return false;
		}
		
		return tickerSymbol.equals(other.tickerSymbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(keyMetricsList, tickerSymbol, lastModifiedTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyKeyMetricsRegister other = (CompanyKeyMetricsRegister) obj;
		return sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return "KeyMetricsRegister [tickerSymbol=" + tickerSymbol + ", lastModifiedTime=" + lastModifiedTime
				+ ", keyMetricsList=" + keyMetricsList + "]";
	}
	
}
