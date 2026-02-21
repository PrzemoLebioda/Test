package com.comida.sia.fundamentals.domain.model.earnings.estimation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "EarningEstimatesReport")
@Table(name = "fundamentals_earnings_estimates_reports",
indexes = {
		@Index(name = "earnings_estimates_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "earnings_estimates_reports_horizon_idx", columnList = "horizon"),
		@Index(name = "earnings_estimates_reports_date_idx", columnList = "date")
})
@Getter
public class EarningEstimatesReport implements ValueObject<EarningEstimatesReport>,  Comparable<EarningEstimatesReport>, TimeSeries {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5839951106192952476L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "date", columnDefinition = "date", nullable = false)
	private Date date;	
	
	@Column(name = "horizon", nullable = false)
	@Enumerated(EnumType.STRING)
    private Horizon horizon;
	
	@Column(name = "eps_estimate_average", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAverage;
	
	@Column(name = "eps_estimate_high", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateHigh;
	
	@Column(name = "eps_estimate_low", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateLow;
	
	@Column(name = "eps_estimate_analyst_count", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAnalystCount;
	
	@Column(name = "eps_estimate_average_7_days_ago", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAverage7DaysAgo;
	
	@Column(name = "eps_estimate_average_30_days_ago", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAverage30DaysAgo;
	
	@Column(name = "eps_estimate_average_60_days_ago", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAverage60DaysAgo;
	
	@Column(name = "eps_estimate_average_90_days_ago", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal epsEstimateAverage90DaysAgo;
	
	@Column(name = "eps_estimate_revision_up_trailing_7_days", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal epsEstimateRevisionUpTrailing7Days;	
	
	@Column(name = "eps_estimate_revision_down_trailing_7_days", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal epsEstimateRevisionDownTrailing7Days;	
	
	@Column(name = "eps_estimate_revision_up_trailing_30_days", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal epsEstimateRevisionUpTrailing30Days;	
	
	@Column(name = "eps_estimate_revision_down_trailing_30_days", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal epsEstimateRevisionDownTrailing30Days;	
    
    @Column(name = "revenue_estimate_average", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal revenueEstimateAverage;					
    
    @Column(name = "revenue_estimate_high", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal revenueEstimateHigh;						
    
    @Column(name = "revenue_estimate_low", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal revenueEstimateLow;				
    
    @Column(name = "revenue_estimate_analyst_count", columnDefinition = "numeric(36,4)", nullable = false)
    private BigDecimal revenueEstimateAnalystCount;
    
    public EarningEstimatesReport(Builder builder) {
    	id = builder.id;
    	tickerSymbol = builder.tickerSymbol;
    	date = builder.date;
    	horizon = builder.horizon;
    	epsEstimateAverage = builder.epsEstimateAverage;                   
    	epsEstimateHigh = builder.epsEstimateHigh;                      
    	epsEstimateLow = builder.epsEstimateLow;                       
    	epsEstimateAnalystCount = builder.epsEstimateAnalystCount;              
    	epsEstimateAverage7DaysAgo = builder.epsEstimateAverage7DaysAgo;           
    	epsEstimateAverage30DaysAgo = builder.epsEstimateAverage30DaysAgo;          
    	epsEstimateAverage60DaysAgo = builder.epsEstimateAverage60DaysAgo;          
    	epsEstimateAverage90DaysAgo = builder.epsEstimateAverage90DaysAgo;          
    	epsEstimateRevisionUpTrailing7Days = builder.epsEstimateRevisionUpTrailing7Days;	 
    	epsEstimateRevisionDownTrailing7Days = builder.epsEstimateRevisionDownTrailing7Days;	
    	epsEstimateRevisionUpTrailing30Days = builder.epsEstimateRevisionUpTrailing30Days;	 
    	epsEstimateRevisionDownTrailing30Days = builder.epsEstimateRevisionDownTrailing30Days;
    	revenueEstimateAverage = builder.revenueEstimateAverage;					
    	revenueEstimateHigh = builder.revenueEstimateHigh;						
    	revenueEstimateLow = builder.revenueEstimateLow;						
    	revenueEstimateAnalystCount = builder.revenueEstimateAnalystCount;          
    }
    
	@Override
	public Date occuranceTime() {
		return date;
	}
	
	@Override
	public int compareTo(EarningEstimatesReport o) {
		if (this.date == null || o.date == null) {
			return 0;
		} else {
			return this.date.compareTo(o.date);
		}
	}
	
	@Override
	public boolean sameValueAs(EarningEstimatesReport other) {
		if(other != null) {
			return  (this.tickerSymbol.equals(other.tickerSymbol)) &&
					(TranslationConcern.sameDates(this.date, other.date)) &&
					(this.horizon == null && other.horizon == null) || (this.horizon != null && this.horizon.equals(other.horizon)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAverage, other.epsEstimateAverage)) &&					
					(TranslationConcern.sameNumbers(this.epsEstimateHigh, other.epsEstimateHigh)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateLow, other.epsEstimateLow)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAnalystCount, other.epsEstimateAnalystCount)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAverage7DaysAgo, other.epsEstimateAverage7DaysAgo)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAverage30DaysAgo, other.epsEstimateAverage30DaysAgo)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAverage60DaysAgo, other.epsEstimateAverage60DaysAgo)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateAverage90DaysAgo, other.epsEstimateAverage90DaysAgo)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateRevisionUpTrailing7Days, other.epsEstimateRevisionUpTrailing7Days)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateRevisionDownTrailing7Days, other.epsEstimateRevisionDownTrailing7Days)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateRevisionUpTrailing30Days, other.epsEstimateRevisionUpTrailing30Days)) &&
					(TranslationConcern.sameNumbers(this.epsEstimateRevisionDownTrailing30Days, other.epsEstimateRevisionDownTrailing30Days)) &&
					(TranslationConcern.sameNumbers(this.revenueEstimateAverage, other.revenueEstimateAverage)) &&
					(TranslationConcern.sameNumbers(this.revenueEstimateHigh, other.revenueEstimateHigh)) &&
					(TranslationConcern.sameNumbers(this.revenueEstimateLow, other.revenueEstimateLow)) &&
					(TranslationConcern.sameNumbers(this.revenueEstimateAnalystCount, other.revenueEstimateAnalystCount))
					;
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, epsEstimateAnalystCount, epsEstimateAverage, epsEstimateAverage30DaysAgo,
				epsEstimateAverage60DaysAgo, epsEstimateAverage7DaysAgo, epsEstimateAverage90DaysAgo, epsEstimateHigh,
				epsEstimateLow, epsEstimateRevisionDownTrailing30Days, epsEstimateRevisionDownTrailing7Days,
				epsEstimateRevisionUpTrailing30Days, epsEstimateRevisionUpTrailing7Days, horizon, id,
				revenueEstimateAnalystCount, revenueEstimateAverage, revenueEstimateHigh, revenueEstimateLow,
				tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EarningEstimatesReport other = (EarningEstimatesReport) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "EarningEstimatesReport [id=" + id + ", tickerSymbol=" + tickerSymbol + ", date=" + date + ", horizon=" + horizon
				+ ", epsEstimateAverage=" + epsEstimateAverage + ", epsEstimateHigh=" + epsEstimateHigh
				+ ", epsEstimateLow=" + epsEstimateLow + ", epsEstimateAnalystCount=" + epsEstimateAnalystCount
				+ ", epsEstimateAverage7DaysAgo=" + epsEstimateAverage7DaysAgo + ", epsEstimateAverage30DaysAgo="
				+ epsEstimateAverage30DaysAgo + ", epsEstimateAverage60DaysAgo=" + epsEstimateAverage60DaysAgo
				+ ", epsEstimateAverage90DaysAgo=" + epsEstimateAverage90DaysAgo
				+ ", epsEstimateRevisionUpTrailing7Days=" + epsEstimateRevisionUpTrailing7Days
				+ ", epsEstimateRevisionDownTrailing7Days=" + epsEstimateRevisionDownTrailing7Days
				+ ", epsEstimateRevisionUpTrailing30Days=" + epsEstimateRevisionUpTrailing30Days
				+ ", epsEstimateRevisionDownTrailing30Days=" + epsEstimateRevisionDownTrailing30Days
				+ ", revenueEstimateAverage=" + revenueEstimateAverage + ", revenueEstimateHigh=" + revenueEstimateHigh
				+ ", revenueEstimateLow=" + revenueEstimateLow + ", revenueEstimateAnalystCount="
				+ revenueEstimateAnalystCount + "]";
	}	
	
	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private Date date;										
	    private Horizon horizon;
	    private BigDecimal epsEstimateAverage;
	    private BigDecimal epsEstimateHigh;
	    private BigDecimal epsEstimateLow;
	    private BigDecimal epsEstimateAnalystCount;
	    private BigDecimal epsEstimateAverage7DaysAgo;
	    private BigDecimal epsEstimateAverage30DaysAgo;
	    private BigDecimal epsEstimateAverage60DaysAgo;
	    private BigDecimal epsEstimateAverage90DaysAgo;
	    private BigDecimal epsEstimateRevisionUpTrailing7Days;	
	    private BigDecimal epsEstimateRevisionDownTrailing7Days;	
	    private BigDecimal epsEstimateRevisionUpTrailing30Days;	
	    private BigDecimal epsEstimateRevisionDownTrailing30Days;	
	    private BigDecimal revenueEstimateAverage;					
	    private BigDecimal revenueEstimateHigh;						
	    private BigDecimal revenueEstimateLow;						
	    private BigDecimal revenueEstimateAnalystCount;
	    
	    public Builder(UUID id, String tickerSymbol) {
	    	setId(id);
	    	setTickerSymbol(tickerSymbol);
	    }
	    
	    private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id is necessary in order to build earnings report");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is necessary in order to build earnings report");
			this.tickerSymbol = tickerSymbol;
		}
		
		public Builder date(Date date) {
			AssertionConcern.assertNotNull(date, "Date is mandatory in order to create earnings estiate report instance");
			this.date = date;
			
			return this;
		}
		
	    public Builder horizon(Horizon horizon) {
	    	AssertionConcern.assertNotNull(horizon, "horizon is mandatory in order to create earnings estiate report instance");
	    	this.horizon = horizon;
	    	
	    	return this;
	    }
	    
	    public Builder epsEstimateAverage(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate average is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAverage = value;
	    	
	    	return this;
	    }
	    
	    public Builder epsEstimateHigh(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate high is mandatory in order to create earnings estiate report instance");
	    	epsEstimateHigh = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateLow(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate low is mandatory in order to create earnings estiate report instance");
	    	epsEstimateLow = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateAnalystCount(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate analyst count is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAnalystCount = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateAverage7DaysAgo(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate average 7 days ago is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAverage7DaysAgo = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateAverage30DaysAgo(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate average 30 days ago is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAverage30DaysAgo = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateAverage60DaysAgo(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate average 60 days ago is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAverage60DaysAgo = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateAverage90DaysAgo(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "EPS estimate average 90 days ago is mandatory in order to create earnings estiate report instance");
	    	epsEstimateAverage90DaysAgo = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateRevisionUpTrailing7Days(BigDecimal value) {
	    	//AssertionConcern.assertNotNull(value, "... is mandatory in order to create earnings estiate report instance");
	    	epsEstimateRevisionUpTrailing7Days = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateRevisionDownTrailing7Days(BigDecimal value) {
	    	//AssertionConcern.assertNotNull(value, "... is mandatory in order to create earnings estiate report instance");
	    	epsEstimateRevisionDownTrailing7Days = value;
		    	
		    return this;
	    }
	    
	    public Builder epsEstimateRevisionUpTrailing30Days(BigDecimal value) {
	    	//AssertionConcern.assertNotNull(value, "... is mandatory in order to create earnings estiate report instance");
	    	epsEstimateRevisionUpTrailing30Days = value;
		    	
		    	return this;
	    }
	    
	    public Builder epsEstimateRevisionDownTrailing30Days(BigDecimal value) {
	    	//AssertionConcern.assertNotNull(value, "... is mandatory in order to create earnings estiate report instance");
	    	epsEstimateRevisionDownTrailing30Days = value;
		    
		    return this;
	    }
	    
	    public Builder revenueEstimateAverage(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "Revenue estimate average is mandatory in order to create earnings estiate report instance");
	    	revenueEstimateAverage = value;
		    	
		    return this;
	    }
	    
	    public Builder revenueEstimateHigh(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "Revenue estimate high is mandatory in order to create earnings estiate report instance");
	    	revenueEstimateHigh = value;
		    	
		    return this;
	    }
	    
	    public Builder revenueEstimateLow(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "Revenue estimate low is mandatory in order to create earnings estiate report instance");
	    	revenueEstimateLow = value;
		    	
		    return this;
	    }
	    
	    public Builder revenueEstimateAnalystCount(BigDecimal value) {
	    	AssertionConcern.assertNotNull(value, "Revenue estimate analyst count is mandatory in order to create earnings estiate report instance");
	    	revenueEstimateAnalystCount = value;
		    	
		    return this;
	    }
	    
		public EarningEstimatesReport build() {
			return new EarningEstimatesReport(this);
		}
	}
	
}
