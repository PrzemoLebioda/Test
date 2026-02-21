package com.comida.sia.fundamentals.domain.model.earnings;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
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

@Entity(name = "EarningReport")
@Table(name = "fundamentals_earnings_reports",
indexes = {
		@Index(name = "earnings_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "earnings_reports_type_idx", columnList = "type"),
		@Index(name = "earnings_reports_fiscal_date_ending_idx", columnList = "fiscal_date_ending")
})
@Getter
public class EarningReport implements ValueObject<EarningReport>,  Comparable<EarningReport>, TimeSeries{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3657543499341629155L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReportType type;
	
	@Column(name = "fiscal_date_ending", columnDefinition = "date", nullable = false)
	private Date fiscalDateEnding;
	
	@Column(name = "reported_eps", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal reportedEPS;
	
	@Column(name = "reported_date", columnDefinition = "date", nullable = true)
    private Date reportedDate;
	
	@Column(name = "estimated_eps", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal estimatedEPS;
	
	@Column(name = "surprise", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal surprise;
	
	@Column(name = "surprise_percentage", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal surprisePercentage;
	
	@Column(name = "report_time", nullable = true)
    private String reportTime;
    
    public EarningReport() {
    	super();
    }
    
    public EarningReport(EarningsReportBuilder builder) {
    	this.id = builder.id;
    	this.tickerSymbol = builder.tickerSymbol;
    	this.type = builder.type;
    	this.fiscalDateEnding = builder.fiscalDateEnding;
    	this.reportedEPS = builder.reportedEPS;
    	
    	this.reportedDate = builder.reportedDate;
    	this.estimatedEPS = builder.estimatedEPS;
    	this.surprise = builder.surprise;
    	this.surprisePercentage = builder.surprisePercentage;
    	this.reportTime = builder.reportTime;
    }
    
    public void setId(UUID id) {
    	AssertionConcern.assertNotNull(id, "Earnings id can't be empty");
    	
    	this.id = id;
    }

	@Override
	public int compareTo(EarningReport o) {
		if (this.fiscalDateEnding == null || o.fiscalDateEnding == null) {
			return 0;
		} else {
			return this.fiscalDateEnding.compareTo(o.fiscalDateEnding);
		}
	}

	@Override
	public boolean sameValueAs(EarningReport other) {
		if(other != null) {
			return  (this.tickerSymbol.equals(other.tickerSymbol)) &&
					(this.type.equals(other.type)) &&
					(TranslationConcern.sameDates(this.fiscalDateEnding, other.fiscalDateEnding)) &&
					(TranslationConcern.sameDates(this.reportedDate, other.reportedDate)) &&
					(TranslationConcern.sameNumbers(this.reportedEPS, other.reportedEPS)) &&					
					(TranslationConcern.sameNumbers(this.estimatedEPS, other.estimatedEPS)) &&
					(TranslationConcern.sameNumbers(this.surprise, other.surprise)) &&
					(TranslationConcern.sameNumbers(this.surprisePercentage, other.surprisePercentage)) &&
					(TranslationConcern.sameTexts(this.reportTime, other.reportTime));
		}else {
			return false;
		}
	}

	@Override
	public Date occuranceTime() {
		return fiscalDateEnding;
	}

	
	@Override
	public String toString() {
		return "EarningReport [id=" + id + ", tickerSymbol=" + tickerSymbol + ", type=" + type + ", fiscalDateEnding="
				+ fiscalDateEnding + ", reportedEPS=" + reportedEPS + ", reportedDate=" + reportedDate
				+ ", estimatedEPS=" + estimatedEPS + ", surprise=" + surprise + ", surprisePercentage="
				+ surprisePercentage + ", reportTime=" + reportTime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(estimatedEPS, fiscalDateEnding, id, reportTime, reportedDate, reportedEPS, surprise,
				surprisePercentage, tickerSymbol, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EarningReport other = (EarningReport) obj;
		return sameValueAs(other);
	}
	
	public static class EarningsReportBuilder{
		protected UUID id;
		protected String tickerSymbol;
		protected ReportType type;
		protected Date fiscalDateEnding;
		protected BigDecimal reportedEPS;
		protected Date reportedDate;
		protected BigDecimal estimatedEPS;
		protected BigDecimal surprise;
		protected BigDecimal surprisePercentage;
		protected String reportTime;
		
		public EarningsReportBuilder(UUID id, String tickerSymbol, ReportType type){
			setId(id);
			setTickerSymbol(tickerSymbol);
			setType(type);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id is necessary in order to build earnings report");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is necessary in order to build earnings report");
			this.tickerSymbol = tickerSymbol;
		}
		
		private void setType(ReportType type) {
			AssertionConcern.assertNotNull(type, "Report type is necessary in order to build earnings report");
			this.type = type;
		}
		
		public EarningsReportBuilder setFiscalDateEnding(Date fiscalDateEnding) {
			AssertionConcern.assertNotNull(fiscalDateEnding, "Report type is necessary in order to build earnings report");
			this.fiscalDateEnding = fiscalDateEnding;
			
			return this;
		}
		
		public EarningsReportBuilder setReportedEPS(BigDecimal reportedEPS) {
			this.reportedEPS = reportedEPS;
			
			return this;
		}
		
		public EarningsReportBuilder setReportedDate(Date reportedDate) {
			this.reportedDate = reportedDate;
			return this;
		}
		
		public EarningsReportBuilder setEstimatedEPS(BigDecimal estimatedEPS) {
			this.estimatedEPS = estimatedEPS;
			return this;
		}
		
		public EarningsReportBuilder setSurprise(BigDecimal surprise) {
			this.surprise = surprise;
			return this;
		}
		
		public EarningsReportBuilder setSurprisePercentage(BigDecimal surprisePercentage) {
			this.surprisePercentage = surprisePercentage;
			return this;
		}
		
		public EarningsReportBuilder setReportTime(String reportTime) {
			this.reportTime = reportTime;
			return this;
		}

		public EarningReport build() {
			return new EarningReport(this);
		}
	}
}
