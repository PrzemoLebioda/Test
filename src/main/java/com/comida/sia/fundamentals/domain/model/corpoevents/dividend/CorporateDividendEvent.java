package com.comida.sia.fundamentals.domain.model.corpoevents.dividend;

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

/**
 * Fields explanation:
 * <ul>
 *  <li><b>exDividendDate:</b> An ex-dividend date is the date before which an investor must own a stock to be eligible to receive the next dividend payment.</li>
 *  <li><b>declarationDate:</b> The declaration date is the date on which the board of directors of a company announces the next dividend payment. This statement includes the dividend's size, ex-dividend date, and payment date. The declaration date is also referred to as the "announcement date."</li>
 * 	<li><b>recordDate:</b> The record date, or date of record, is the cutoff date established by a company to determine which shareholders are officially on the books and are therefore eligible to receive an upcoming dividend.</li> 
 * 	<li><b>paymentDate:</b> A payment date, also known as the pay or payable date, is the day on which a declared stock dividend is scheduled to be paid to eligible investors. This date can be up to a month after the ex-dividend date. Note that the stock price may fall on the payment date to reflect the dividend payment even if it has not been actually credited to investors at that point in time.</li>
 * 	<li><b>amount:</b> dividend value</li>
 * </ul>
 * For further details see: <a href="https://www.investopedia.com/terms/e/ex-date.asp">Ex-Dividend Date: Definition, Key Dates, and Example</a>
 */
@Entity(name = "CorporateDividendEvent")
@Table(name = "fundamentals_corporate_dividend_events",
	indexes = {
		@Index(name = "dividends_events_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "dividends_events_declaration_date_idx", columnList = "declaration_date")
})
@Getter
public class CorporateDividendEvent implements ValueObject<CorporateDividendEvent>, Comparable<CorporateDividendEvent>, TimeSeries {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2868156204130833282L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "ex_dividend_date", columnDefinition = "date", nullable = true)
	private Date exDividendDate;
	
	@Column(name = "declaration_date", columnDefinition = "date", nullable = true)
	private Date declarationDate;
	
	@Column(name = "record_date", columnDefinition = "date", nullable = true)
	private Date recordDate;
	
	@Column(name = "payment_date", columnDefinition = "date", nullable = true)
	private Date paymentDate;
	
	@Column(name = "amount", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal amount;
	
	public CorporateDividendEvent() {
		super();
	}
	
	public CorporateDividendEvent(Builder builder) {
		id = builder.id;
		tickerSymbol = builder.tickerSymbol;
		exDividendDate = builder.exDividendDate;
		declarationDate = builder.declarationDate;
		recordDate = builder.recordDate;
		paymentDate = builder.paymentDate;
		amount = builder.amount;
	}
	
	public Boolean isDeclaration() {
		Date currentDate = new Date();
		
		return this.exDividendDate.before(currentDate) ||
			this.declarationDate.before(currentDate) ||
			this.recordDate.before(currentDate) ||
			this.paymentDate.before(currentDate);
	}
	
	public Boolean isHistorical() {	
		return ! isDeclaration();
	}
	
	@Override
	public Date occuranceTime() {
		return declarationDate == null ? exDividendDate : declarationDate;
	}

	@Override
	public int compareTo(CorporateDividendEvent o) {
		if (this.declarationDate == null || o.declarationDate == null) {
			return 0;
		} else {
			return this.declarationDate.compareTo(o.declarationDate);
		}
	}

	@Override
	public boolean sameValueAs(CorporateDividendEvent other) {
		if(other != null) {
			return  (ComparationConcern.sameTexts(this.tickerSymbol, other.tickerSymbol)) &&
					(ComparationConcern.sameDates(this.exDividendDate, other.exDividendDate)) &&
					(ComparationConcern.sameDates(this.declarationDate, other.declarationDate)) &&
					(ComparationConcern.sameDates(this.recordDate, other.recordDate)) &&
					(ComparationConcern.sameDates(this.paymentDate, other.paymentDate)) &&
					(ComparationConcern.sameNumbers(this.amount, other.amount));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, declarationDate, exDividendDate, id, paymentDate, recordDate, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorporateDividendEvent other = (CorporateDividendEvent) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "CorporateDividentEvent [id=" + id + ", exDividendDate="
				+ exDividendDate + ", declarationDate=" + declarationDate + ", recordDate=" + recordDate
				+ ", paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}
	
	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private Date exDividendDate;
		private Date declarationDate;
		private Date recordDate;
		private Date paymentDate;
		private BigDecimal amount;
		
		public Builder(UUID id, String tickerSymbol) {
			setId(id);
			setTickerSymbol(tickerSymbol);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Corporate divident event's id can't be empty");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create corporate divident event");
			this.tickerSymbol = tickerSymbol;
		}
		
		public Builder exDividendDate(Date exDividendDate) {
			this.exDividendDate = exDividendDate;
			return this;
		}
		
		public Builder declarationDate(Date declarationDate) {
			this.declarationDate = declarationDate;
			return this;
		}
		
		public Builder recordDate(Date recordDate) {
			this.recordDate = recordDate;
			return this;
		}
		
		public Builder paymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
			return this;
		}
		
		public Builder amount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}
		
		public CorporateDividendEvent build() {
			return new CorporateDividendEvent(this);
		}
	}
	
}
