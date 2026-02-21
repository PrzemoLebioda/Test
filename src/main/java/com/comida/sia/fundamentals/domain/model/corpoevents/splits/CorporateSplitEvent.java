package com.comida.sia.fundamentals.domain.model.corpoevents.splits;

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
 * <b>Stock split definition:</b>
 * <br>
 * A stock split is when a corporation splits its outstanding stocks to increase the number of shares on the market, which boosts liquidity and decreases its stock price.
 * <p>
 * Fields explanation:
 * 	<ul>
 * 		<li><b>effectiveDate:</b>  the distribution date is when the new shares are actually issued and begin trading at the post-split price.</li>
 * 		<li><b>splitFactor:</b> </li>
 * 	</ul>
 * For further details see: https://www.investopedia.com/terms/s/stocksplit.asp
 * </p>
 */
@Entity(name = "CorporateSplitEvent")
@Table(name = "fundamentals_corporate_split_events",
	indexes = {
		@Index(name = "split_events_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "split_events_effective_date_idx", columnList = "effective_date")
})
@Getter
public class CorporateSplitEvent implements ValueObject<CorporateSplitEvent>, Comparable<CorporateSplitEvent>, TimeSeries {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1384594593126186606L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "effective_date", columnDefinition = "date", nullable = false)
	private Date effectiveDate;
	
	@Column(name = "split_factor", columnDefinition = "numeric(36,4)", nullable = false)
	private BigDecimal splitFactor;
	
	public CorporateSplitEvent() {
		super();
	}
	
	public CorporateSplitEvent(Builder builder) {
		id = builder.id;
		tickerSymbol = builder.tickerSymbol;
		effectiveDate = builder.effectiveDate;
		splitFactor = builder.splitFactor;
	}	

	@Override
	public int compareTo(CorporateSplitEvent o) {
		if (this.effectiveDate == null || o.effectiveDate == null) {
			return 0;
		} else {
			return this.effectiveDate.compareTo(o.effectiveDate);
		}
	}

	@Override
	public boolean sameValueAs(CorporateSplitEvent other) {
		if(other != null) {
			return  (ComparationConcern.sameTexts(this.tickerSymbol, other.tickerSymbol)) &&
					(ComparationConcern.sameDates(this.effectiveDate, other.effectiveDate)) &&
					(ComparationConcern.sameNumbers(this.splitFactor, other.splitFactor));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(effectiveDate, id, splitFactor, tickerSymbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorporateSplitEvent other = (CorporateSplitEvent) obj;
		return sameValueAs(other);
	}

	@Override
	public Date occuranceTime() {
		return effectiveDate;
	}

	@Override
	public String toString() {
		return "CorporateSplitEvent [id=" + id + ", tickerSymbol=" + tickerSymbol + ", effectiveDate=" + effectiveDate
				+ ", splitFactor=" + splitFactor + "]";
	}

	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private Date effectiveDate;
		private BigDecimal splitFactor;
		
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
		
		public Builder effectiveDate(Date effectiveDate) {
			AssertionConcern.assertNotNull(effectiveDate, "Corporate split event's effective dane can't be null");
			this.effectiveDate = effectiveDate;
			
			return this;
		}
		
		public Builder splitFactor(BigDecimal splitFactor) {
			AssertionConcern.assertNotNull(splitFactor, "Corporate split event's split factor can't be null");
			this.splitFactor = splitFactor;
			
			return this;
		}
		
		public CorporateSplitEvent build() {
			return new CorporateSplitEvent(this);
		}
	}
}
