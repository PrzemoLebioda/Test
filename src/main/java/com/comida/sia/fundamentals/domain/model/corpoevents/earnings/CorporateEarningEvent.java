package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;

@Entity(name = "CorporateEarningEvent")
@Table(name = "fundamentals_corporate_earnings_events",
	indexes = {
		@Index(name = "earnings_events_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "earnings_events_report_date_idx", columnList = "report_date"),
		@Index(name = "earnings_events_fiscal_date_ending_idx", columnList = "fiscal_date_ending")
})
@Getter
public class CorporateEarningEvent implements ValueObject<CorporateEarningEvent>, Comparable<CorporateEarningEvent>, TimeSeries{

	@Transient
	private Notifier notifier;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6646237851248338318L;

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "report_date", columnDefinition = "date", nullable = false)
	private Date reportDate;
	
	@Column(name = "fiscal_date_ending", columnDefinition = "date", nullable = false)
	private Date fiscalDateEnding;
	
	@Column(name = "estimate", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal estimate;
	
	@Column(name = "reported_currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private CurrencySymbol currencySymbol;
	
	public CorporateEarningEvent() {
		super();
	}
	
	public CorporateEarningEvent (Builder builder) {
		id = builder.id;
		tickerSymbol = builder.tickerSymbol;
		reportDate = builder.reportDate;
		fiscalDateEnding = builder.fiscalDateEnding;
		estimate = builder.estimate;
		currencySymbol = builder.currencySymbol;
	}
	
	public CorporateEarningEvent withNotifier(Notifier notifier) {
		this.notifier = notifier;
		return this;
	}
	
	public void addToCalendar() {
		notifier.notify(new EarningsCalendarUpdatedDomainEvent(tickerSymbol, reportDate));
	}

	@Override
	public int compareTo(CorporateEarningEvent o) {
		if (this.reportDate == null || o.reportDate == null) {
			return 0;
		} else {
			return this.reportDate.compareTo(o.reportDate);
		}
	}
	
	@Override
	public Date occuranceTime() {
		return reportDate;
	}

	@Override
	public boolean sameValueAs(CorporateEarningEvent other) {
		if(other != null) {
			return  (ComparationConcern.sameTexts(this.tickerSymbol, other.tickerSymbol)) &&
					(ComparationConcern.sameDates(this.fiscalDateEnding, other.fiscalDateEnding)) &&
					(ComparationConcern.sameDates(this.reportDate, other.reportDate)) &&
					(ComparationConcern.sameNumbers(this.estimate, other.estimate)) &&
					((this.currencySymbol == null && other.currencySymbol == null) || (this.currencySymbol != null && this.currencySymbol.equals(other.currencySymbol)));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(currencySymbol, estimate, fiscalDateEnding, id, reportDate, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorporateEarningEvent other = (CorporateEarningEvent) obj;
		return this.sameValueAs(other);
	}

	
	@Override
	public String toString() {
		return "CorporateEarningEvent [id=" + id + ", tickerSymbol=" + tickerSymbol + ", reportDate=" + reportDate
				+ ", fiscalDateEnding=" + fiscalDateEnding + ", estimate=" + estimate + ", currencySymbol="
				+ currencySymbol + "]";
	}


	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private Date reportDate;
		private Date fiscalDateEnding;
		private BigDecimal estimate;
		private CurrencySymbol currencySymbol;
		
		public Builder(UUID id, String tickerSymbol) {
			setId(id);
			setTickerSymbol(tickerSymbol);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Corporate earning event's id can't be empty");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create corporate earnings event");
			this.tickerSymbol = tickerSymbol;
		}
		
		public Builder reportedDate(Date reportDate) {
			AssertionConcern.assertNotNull(reportDate, "Report date is mandatory in order to create corporate earnings event");
			this.reportDate = reportDate;
			return this;
		}
		
		public Builder fiscalDateEnding(Date fiscalDateEnding) {
			AssertionConcern.assertNotNull(fiscalDateEnding, "Fiscal date ending is mandatory in order to create corporate earnings event");
			this.fiscalDateEnding = fiscalDateEnding;
			return this;
		}
		
		public Builder estimate(BigDecimal estimate) {
			this.estimate = estimate;
			return this;
		}
		
		public Builder currencySymbol(CurrencySymbol currencySymbol) {
			AssertionConcern.assertNotNull(currencySymbol, "Currency symvol is mandatory in order to create corporate earnings event");
			this.currencySymbol = currencySymbol;
			return this;
		}
		
		public CorporateEarningEvent build() {
			return new CorporateEarningEvent(this);
		}
	}
}
