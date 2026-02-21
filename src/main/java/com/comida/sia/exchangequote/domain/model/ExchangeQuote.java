package com.comida.sia.exchangequote.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.company.Exchange;
import com.comida.sia.fundamentals.domain.model.company.Industry;
import com.comida.sia.fundamentals.domain.model.company.Sector;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sharedkernel.period.Period;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity(name = "ExchangeQuote")
@Table(name = "exchange_quotes",
		indexes = {
				@Index(name = "exchange_quote_exchange_idx", columnList = "exchange"),
				@Index(name = "exchange_quote_sector_idx", columnList = "sector"),
				@Index(name = "exchange_quote_industry_idx", columnList = "industry"),
				@Index(name = "exchange_quote_ticker_symbol_idx", columnList = "ticker_symbol"),
				@Index(name = "exchange_quote_quotation_type_idx", columnList = "quotation_type"),
				@Index(name = "exchange_quote_period_type_idx", columnList = "period_type"),
				@Index(name = "exchange_quote_period_value_idx", columnList = "period_value"),
				
		})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="quotation_type", 
					 discriminatorType = DiscriminatorType.STRING)
@Getter
public abstract class ExchangeQuote implements ValueObject<ExchangeQuote>, TimeSeries{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2876586164136999112L;

	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "exchange", nullable = false) //
	@Enumerated(EnumType.STRING)
	private Exchange exchange;
	
	@Id
	@Column(name = "sector", nullable = false) 
	@Enumerated(EnumType.STRING)
	private Sector sector;
	
	@Id
	@Column(name = "industry", nullable = false) //
	@Enumerated(EnumType.STRING)
	private Industry industry;
	
	@Column(name = "ticker_symbol", nullable = false) //
	private String tickerSymbol;
	
	@Embedded
	private Period period;
	
	@Column(name = "quotation_time", nullable = false) //
	private Date quotationTime;
	
	@Column(name = "open", columnDefinition = "numeric(36,4)", nullable = false) //
	private BigDecimal open;
	
	@Column(name = "high", columnDefinition = "numeric(36,4)", nullable = false)//
	private BigDecimal high;
	
	@Column(name = "low", columnDefinition = "numeric(36,4)", nullable = false) //
	private BigDecimal low;
	
	@Column(name = "close", columnDefinition = "numeric(36,4)", nullable = false) //
	private BigDecimal close;
	
	@Column(name = "volume", nullable = false) //
	private Long volume; 
	
	@Column(name = "adjusted_close", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal adjustedClose;
	
	@Column(name = "dividend_amount", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal dividendAmount;
	
	@Column(name = "split_coefficient", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal splitCoefficient;
	
	
	public ExchangeQuote() {
		super();
	}
	
	public ExchangeQuote(UUID id) {
		super();
		this.setId(id);
	}
	
	@Override
	public Date occuranceTime() {
		return quotationTime;
	}
	
	@Override
	public boolean sameValueAs(ExchangeQuote other) {
		if(other != null) {
			return  this.period.sameValueAs(other.period) &&
					ComparationConcern.sameTexts(this.tickerSymbol, other.tickerSymbol) &&
					ComparationConcern.sameDates(this.quotationTime, other.quotationTime) &&
					ComparationConcern.sameNumbers(this.open, other.open) &&
					ComparationConcern.sameNumbers(this.high, other.high) &&
					ComparationConcern.sameNumbers(this.low, other.low) &&
					ComparationConcern.sameNumbers(this.close, other.close) &&
					ComparationConcern.sameNumbers(this.volume, other.volume) &&
					ComparationConcern.sameNumbers(this.adjustedClose, other.adjustedClose) &&
					ComparationConcern.sameNumbers(this.dividendAmount, other.dividendAmount) &&
					ComparationConcern.sameNumbers(this.splitCoefficient, other.splitCoefficient);
		}else {
			return false;
		}
	}
	
	protected void setId(UUID id) {
		AssertionConcern.assertNotNull(id, "exchange quotation id can't be null");
		this.id = id;
	}
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	protected void setQuotationTime(Date quotationTime) {
		this.quotationTime = quotationTime;
	}

	protected void setOpen(BigDecimal open) {
		this.open = open;
	}

	protected void setHigh(BigDecimal high) {
		this.high = high;
	}

	protected void setLow(BigDecimal low) {
		this.low = low;
	}

	protected void setClose(BigDecimal close) {
		this.close = close;
	}

	protected void setVolume(Long volume) {
		this.volume = volume;
	}

	protected void setAdjustedClose(BigDecimal adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	protected void setDividendAmount(BigDecimal dividendAmount) {
		this.dividendAmount = dividendAmount;
	}

	protected void setSplitCoefficient(BigDecimal splitCoefficient) {
		this.splitCoefficient = splitCoefficient;
	}

	@Override
	public String toString() {
		return "ExchangeQuote [id=" + id + ", exchange=" + exchange + ", sector=" + sector + ", industry=" + industry
				+ ", tickerSymbol=" + tickerSymbol + ", period=" + period + ", quotationTime=" + quotationTime
				+ ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume
				+ ", adjustedClose=" + adjustedClose + ", dividendAmount=" + dividendAmount + ", splitCoefficient="
				+ splitCoefficient + "]";
	}
	
	@Getter
	public static abstract class Builder<T extends ExchangeQuote>{
		private UUID id;
		private Exchange exchange;
		private Sector sector;
		private Industry industry;
		private String tickerSymbol;
		private Date quotationTime;
		private BigDecimal open;
		private BigDecimal high;
		private BigDecimal low;
		private BigDecimal close;
		private Long volume; 
		private BigDecimal adjustedClose;
		private BigDecimal dividendAmount;
		private BigDecimal splitCoefficient;
		
		public Builder(UUID id) {
			id(id);
		}
		
		private void id(UUID id) {
			AssertionConcern.assertNotNull(id, "Id of the exchange quote entry is mandatory");
			this.id = id;
		}
		
		public Builder<T> exchange(Exchange exchange) {
			AssertionConcern.assertNotNull(exchange, "Exchange can't be empty");
			this.exchange = exchange;
			return this;
		}
		
		public Builder<T> sector(Sector sector) {
			AssertionConcern.assertNotNull(sector, "Sector can't be empty");
			this.sector = sector;
			return this;
		}
		
		public Builder<T> industry(Industry industry) {
			AssertionConcern.assertNotNull(industry, "Industry can't be empty");
			this.industry = industry;
			return this;
		}
		
		public Builder<T> symbol(String symbol) {
			AssertionConcern.assertNotEmpty(symbol, "Symbol cannot be either null or empty");		
			this.tickerSymbol = symbol;
			return this;
		}

		public Builder<T> quotationTime(Date quotationTime) {
			AssertionConcern.assertNotNull(quotationTime, "Quotation time can't be null");
			this.quotationTime = quotationTime;
			return this;
		}
		
		public Builder<T> open(BigDecimal open) {
			AssertionConcern.assertNotNull(open, "Open quotation can't be null");
			this.open = open;
			return this;
		}
		
		public Builder<T> high(BigDecimal high) {
			AssertionConcern.assertNotNull(high, "High quotation can't be null");
			this.high = high;
			return this;
		}
		
		public Builder<T> low(BigDecimal low){
			AssertionConcern.assertNotNull(low, "Low quotation can't be null");
			this.low = low;
			return this;
		}
		
		public Builder<T> close(BigDecimal close) {
			AssertionConcern.assertNotNull(close, "Close quotation can't be null");
			this.close = close;
			return this;
		}
		
		public Builder<T> volume(Long volume) {
			AssertionConcern.assertNotNull(volume, "Volume can't be null");
			this.volume = volume;
			return this;
		}
		
		public Builder<T> adjustedClose(BigDecimal adjustedClose) {
			this.adjustedClose = adjustedClose;
			return this;
		}
		
		public Builder<T> dividendAmount(BigDecimal dividendAmount) {
			this.dividendAmount = dividendAmount;
			return this;
		}
		
		public Builder<T> splitCoefficient(BigDecimal splitCoefficient) {
			this.splitCoefficient = splitCoefficient;
			return this;
		}
	
		public abstract Builder<T> period(Date date);
		public abstract Period getPeriod();
		public abstract T build() ;
	}
}
