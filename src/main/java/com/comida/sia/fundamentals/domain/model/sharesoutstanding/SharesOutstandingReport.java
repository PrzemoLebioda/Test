package com.comida.sia.fundamentals.domain.model.sharesoutstanding;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "SharesOutstandingReport")
@Table(name = "fundamentals_shares_outstanding_reports",
indexes = {
		@Index(name = "shares_outstanding_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "shares_outstanding_reports_date_idx", columnList = "date")
})
@Getter
public class SharesOutstandingReport implements ValueObject<SharesOutstandingReport>, Comparable<SharesOutstandingReport>, TimeSeries  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 861137610532903327L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "date", columnDefinition = "date", nullable = false)
	private Date date;
	
	@Column(name = "shares_outstanding_diluted", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal sharesOutstandingDiluted;
	
	@Column(name = "shares_outstanding_basic", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal sharesOutstandingBasic;
	
	public SharesOutstandingReport(Builder builder) {
		this.id = builder.id;
		this.tickerSymbol = builder.tickerSymbol;
		this.date = builder.date;
		this.sharesOutstandingBasic = builder.sharesOutstandingBasic;
		this.sharesOutstandingDiluted = builder.sharesOutstandingDiluted;
	}
	
	@Override
	public Date occuranceTime() {
		return date;
	}
	
	@Override
	public int compareTo(SharesOutstandingReport o) {
		if (this.date == null || o.date == null) {
			return 0;
		} else {
			return this.date.compareTo(o.date);
		}
	}
	
	@Override
	public boolean sameValueAs(SharesOutstandingReport other) {
		if(other != null) {
			return 	(this.tickerSymbol.equals(other.tickerSymbol)) &&
					(ComparationConcern.sameDates(this.date, other.date)) &&
					(ComparationConcern.sameNumbers(this.sharesOutstandingBasic, other.sharesOutstandingBasic)) &&
					(ComparationConcern.sameNumbers(this.sharesOutstandingDiluted, other.sharesOutstandingDiluted));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, sharesOutstandingBasic, sharesOutstandingDiluted, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharesOutstandingReport other = (SharesOutstandingReport) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "SharesOutstandingReport [tickerSymbol=" + tickerSymbol + ", date=" + date
				+ ", sharesOutstandingDiluted=" + sharesOutstandingDiluted + ", sharesOutstandingBasic="
				+ sharesOutstandingBasic + "]";
	}
	
	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private Date date;
		private BigDecimal sharesOutstandingDiluted;
		private BigDecimal sharesOutstandingBasic;
		
		public Builder(UUID id, String tickerSymbol) {
			setId(id);
			setTickerSymbol(tickerSymbol);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "ID of shares outanding report");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is necessary in order to build income statement report");
			this.tickerSymbol = tickerSymbol;
		}
		
		public Builder date(Date date) {
			AssertionConcern.assertNotNull(date, "Date of shares outstanding report is mandatory");
			this.date = date;
			return this;
		}
		
		public Builder sharesOutstandingDiluted(BigDecimal sharesOutstandingDiluted) {
			AssertionConcern.assertNotNull(sharesOutstandingDiluted, "Diluted shares outstanding is mandatory");
			this.sharesOutstandingDiluted = sharesOutstandingDiluted;
			return this;
		}
		
		public Builder sharesOutstandingBasic(BigDecimal sharesOutstandingBasic) {
			AssertionConcern.assertNotNull(sharesOutstandingBasic, "Basic shares outstanding is mandatory");
			this.sharesOutstandingBasic = sharesOutstandingBasic;
			return this;
		}
		
		public SharesOutstandingReport build() {
			return new SharesOutstandingReport(this);
		}
	}
}
