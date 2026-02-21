package com.comida.sia.options.domain.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import com.comida.sia.fundamentals.domain.model.company.Industry;
import com.comida.sia.fundamentals.domain.model.company.Sector;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.domain.DomainEntity;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "Option")
@Table(name = "options",
		indexes = {
				@Index(name = "options_symbol_idx", columnList = "ticker_symbol"),
				@Index(name = "options_type_idx", columnList = "type")
		})
public class Option implements DomainEntity<Option>,  Comparable<Option>, TimeSeries{
	
	@Id
	@Column(name = "contract_id", nullable = false)
	@Getter private String contractId;
	
	@Id
	@Column(name = "sector", nullable = false) 
	@Enumerated(EnumType.STRING)
	private Sector sector;
	
	@Id
	@Column(name = "industry", nullable = false) //
	@Enumerated(EnumType.STRING)
	private Industry industry;
	
	@Id
	@Column(name = "trading_date", nullable = false)
	@Getter private Date tradingDate;
	
	@Column(name = "ticker_symbol", nullable = false)
	@Getter private String tickerSymbol;
	
	@Column(name = "expiration", nullable = true)
	@Getter private Date expiration;
	
	@Column(name = "strike", columnDefinition = "numeric(36,4)", nullable = false)
	@Getter private BigDecimal strike;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	@Getter private OptionType type;
	
	@Column(name = "last", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal last;
	
	@Column(name = "mark", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal mark;
	
	@Column(name = "bid", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal bid;
	
	@Column(name = "bid_size", nullable = true)
	@Getter private Integer bidSize;
	
	@Column(name = "ask", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal ask;
	
	@Column(name = "ask_size", nullable = true)
	@Getter private Integer askSize;
	
	@Column(name = "volume", nullable = true)
	@Getter private Integer volume;
	
	@Column(name = "open_interest", nullable = true)
	@Getter private Integer openInterest;
	
	@Column(name = "implied_volatility", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal impliedVolatility;
	
	@Column(name = "delta", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal delta;
	
	@Column(name = "gamma", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal gamma;
	
	@Column(name = "theta", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal theta;
	
	@Column(name = "vega", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal vega;
	
	@Column(name = "rho", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal rho;
	
	public Option() {
		
	}
	
	private Option(Builder builder) {
		
		this.contractId = builder.contractId;
		this.industry = builder.industry;
		this.sector = builder.sector;
		this.tickerSymbol = builder.tickerSymbol;
		this.strike = builder.strike;
		this.expiration = builder.expiration;
		this.strike = builder.strike;
		this.type = builder.type;
		this.last = builder.last;
		this.mark = builder.mark;
		this.bid = builder.bid;
		this.bidSize = builder.bidSize;
		this.ask = builder.ask;
		this.askSize = builder.askSize;
		this.volume = builder.volume;
		this.openInterest = builder.openInterest;
		this.tradingDate = builder.tradingDate;
		this.impliedVolatility = builder.impliedVolatility;
		this.delta = builder.delta;
		this.gamma = builder.gamma;
		this.theta = builder.theta;
		this.vega = builder.vega;
		this.rho = builder.rho;
	}
	
	public void setSector(Sector sector) {
		AssertionConcern.assertNotNull(sector, "Sector can't be empty");
		this.sector = sector;
	}
	
	public void setIndustry(Industry industry) {
		AssertionConcern.assertNotNull(industry, "Industry can't be empty");
		this.industry = industry;
	}
	
	@Override
	public Date occuranceTime() {
		return tradingDate;
	}
	
	@Override
	public int compareTo(Option o) {
		if (this.tradingDate == null || o.tradingDate == null) {
			return 0;
		} else {
			return this.tradingDate.compareTo(o.tradingDate);
		}
	}
	
	@Override
	public boolean sameIdentityAs(Option other) {
		if(other == null) {
			return false;
		}
		
		return contractId.equals(other.contractId);
	}


	@Override
	public int hashCode() {
		return Objects.hash(ask, askSize, bid, bidSize, contractId, tradingDate, delta, expiration, gamma, impliedVolatility,
				last, mark, openInterest, rho, strike, tickerSymbol, theta, type, vega, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Option other = (Option) obj;
		return this.sameIdentityAs(other);
	}

	@Override
	public String toString() {
		return "Option [contractId=" + contractId + ", symbol=" + tickerSymbol + ", expiration=" + expiration + ", strike="
				+ strike + ", type=" + type + ", last=" + last + ", mark=" + mark + ", bid=" + bid + ", bidSize="
				+ bidSize + ", ask=" + ask + ", askSize=" + askSize + ", volume=" + volume + ", openInterest="
				+ openInterest + ", date=" + tradingDate + ", impliedVolatility=" + impliedVolatility + ", delta=" + delta
				+ ", gamma=" + gamma + ", theta=" + theta + ", vega=" + vega + ", rho=" + rho + "]";
	}

	public static class Builder {
		private String contractId;
		private Date tradingDate;
		private Sector sector;
		private Industry industry;
		private String tickerSymbol;
		private Date expiration;
		private BigDecimal strike;
		private OptionType type;
		private BigDecimal last;
		private BigDecimal mark;
		private BigDecimal bid;
		private Integer bidSize;
		private BigDecimal ask;
		private Integer askSize;
		private Integer volume;
		private Integer openInterest;
		private BigDecimal impliedVolatility;
		private BigDecimal delta;
		private BigDecimal gamma;
		private BigDecimal theta;
		private BigDecimal vega;
		private BigDecimal rho;
		
		public Builder(String contractId, String tradingDate, String symbol) throws ParseException {
			this.setContractId(contractId);
			this.setTradingDate(tradingDate);
			this.setTickerSymbol(symbol);
		}
		
		private void setContractId(String contractId) {
			AssertionConcern.assertNotEmpty(contractId, "Contract id must be provided");
			this.contractId = contractId;
		}
		
		private Builder setTradingDate(String date) throws ParseException {
			AssertionConcern.assertNotEmpty(date, "Date must be provided");
			this.tradingDate = TranslationConcern.getDateFrom(date);
			return this;
		}
		
		private void setTickerSymbol(String symbol) {
			AssertionConcern.assertNotEmpty(symbol, "Ticker symbol must be provided");
			this.tickerSymbol = symbol;
		}
		
		public Builder sector(Sector sector) {
			AssertionConcern.assertNotNull(sector, "Sector can't be empty");
			this.sector = sector;
			return this;
		}
		
		public Builder industry(Industry industry) {
			AssertionConcern.assertNotNull(industry, "Industry can't be empty");
			this.industry = industry;
			return this;
		}
		
		public Builder setExpiration(String expiration) throws ParseException {
			AssertionConcern.assertNotEmpty(expiration, "Expiration must be provided");
			this.expiration = TranslationConcern.getDateFrom(expiration);
			return this;
		}
		
		public Builder setStrike(String strike) {
			AssertionConcern.assertNotEmpty(strike, "Strike must be provided");
			this.strike = TranslationConcern.getNumberFrom(strike);
			return this;
		}
		
		public Builder setType(String type) {
			AssertionConcern.assertNotEmpty(type, "Type must be provided");
			this.type = OptionType.get(type);
			return this;
		}
		
		public Builder setLast(String last) {
			AssertionConcern.assertNotEmpty(last, "Last must be provided");
			this.last = TranslationConcern.getNumberFrom(last);
			return this;
		}
		
		public Builder setMark(String mark) {
			AssertionConcern.assertNotEmpty(mark, "Mark must be provided");
			this.mark = TranslationConcern.getNumberFrom(mark);
			return this;
		}
		
		public Builder setBid(String bid) {
			AssertionConcern.assertNotEmpty(bid, "Bid must be provided");
			this.bid = TranslationConcern.getNumberFrom(bid);
			return this;
		}
		
		public Builder setBidSize(String bidSize) {
			AssertionConcern.assertNotEmpty(bidSize, "Bid size must be provided");
			this.bidSize = TranslationConcern.getIntegerFrom(bidSize);
			return this;
		}
		
		public Builder setAsk(String ask) {
			AssertionConcern.assertNotEmpty(ask, "Ask must be provided");
			this.ask = TranslationConcern.getNumberFrom(ask);
			return this;
		}
		
		public Builder setAskSize(String askSize) {
			AssertionConcern.assertNotEmpty(askSize, "Ask size must be provided");
			this.askSize = TranslationConcern.getIntegerFrom(askSize);
			return this;
		}
		
		public Builder setVolume(String volume) {
			AssertionConcern.assertNotEmpty(volume, "Volume must be provided");
			this.volume = TranslationConcern.getIntegerFrom(volume);
			return this;
		}
		
		public Builder setOpenInterest(String openInterest) {
			AssertionConcern.assertNotEmpty(openInterest, "Open intrest must be provided");
			this.openInterest = TranslationConcern.getIntegerFrom(openInterest);
			return this;
		}
		
		public Builder setImpliedVolatility(String impliedVolatility) {
			AssertionConcern.assertNotEmpty(impliedVolatility, "Implied volatility must be provided");
			this.impliedVolatility = TranslationConcern.getNumberFrom(impliedVolatility);
			return this;
		}
		
		public Builder setDelta(String delta) {
			AssertionConcern.assertNotNull(delta, "Delta must be provided");
			this.delta = TranslationConcern.getNumberFrom(delta);
			return this;
		}
		
		public Builder setGamma(String gamma) {
			AssertionConcern.assertNotEmpty(gamma, "Gamma must be provided");
			this.gamma = TranslationConcern.getNumberFrom(gamma);
			return this;
		}
		
		public Builder setTheta(String theta) {
			AssertionConcern.assertNotEmpty(theta, "Theta must be provided");
			this.theta = TranslationConcern.getNumberFrom(theta);
			return this;
		}
		
		public Builder setVega(String vega) {
			AssertionConcern.assertNotEmpty(vega, "Vega must be provided");
			this.vega = TranslationConcern.getNumberFrom(vega);
			return this;
		}
		
		public Builder setRho(String rho) {
			AssertionConcern.assertNotEmpty(rho, "Rho must be provided");
			this.rho = TranslationConcern.getNumberFrom(rho);
			return this;
		}
		
		public Option build() {
			return new Option(this);
		}
	}

}
