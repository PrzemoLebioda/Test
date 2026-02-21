package com.comida.sia.intelligence.insidertransactions.domain.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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

@Entity(name = "InsiderTransaction")
@Table(name = "intelligence_insider_transaction",
		indexes = {
				@Index(name = "insiter_transaction_symbol_idx", columnList = "ticker_symbol"),
				@Index(name = "insider_transaction_type_idx", columnList = "transaction_type")
		})
public class InsiderTransaction implements DomainEntity<InsiderTransaction>, TimeSeries {
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	@Getter private String tickerSymbol;
	
	@Column(name = "transaction_date", nullable = false)
	@Getter private Date transactionDate;
	
	@Column(name = "executive", nullable = false)
	@Getter private String executive;
	
	@Column(name = "executive_title", nullable = true)
	@Getter private String executiveTitle;
	
	@Column(name = "security_symbol", nullable = true)
	@Getter private String securityType;
	
	@Column(name = "transaction_type", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private TransactionType transactionType; //acquisition_or_disposal;
	
	@Column(name = "shares", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal shares;
	
	@Column(name = "share_price", columnDefinition = "numeric(36,4)", nullable = true)
	@Getter private BigDecimal sharePrice;
	
	public InsiderTransaction() {
		
	}
	
	public InsiderTransaction(Builder builder) {
		this.id = builder.id;
		this.transactionDate = builder.transactionDate;
		this.tickerSymbol = builder.tickerSymbol;
		this.executive = builder.executive;
		this.executiveTitle = builder.executiveTitle;
		this.securityType = builder.securityType;
		this.transactionType = builder.transactionType;
		this.shares = builder.shares;
		this.sharePrice = builder.sharePrice;
	}
	
	@Override
	public Date occuranceTime() {
		return transactionDate;
	}

	@Override
	public String toString() {
		return "InsiderTransaction [id=" + id + ", transactionDate=" + transactionDate + ", tickerSymbol="
				+ tickerSymbol + ", executive=" + executive + ", executiveTitle=" + executiveTitle + ", securityType="
				+ securityType + ", transactionType=" + transactionType + ", shares=" + shares + ", sharePrice="
				+ sharePrice + "]";
	}

	@Override
	public boolean sameIdentityAs(InsiderTransaction other) {
		if(other == null) {
			return false;
		}
		
		return id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(executive, executiveTitle, id, securityType, sharePrice, shares, tickerSymbol,
				transactionDate, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsiderTransaction other = (InsiderTransaction) obj;
		return sameIdentityAs(other);
	}
	
	public static class Builder{
		private UUID id;
		private Date transactionDate;
		private String tickerSymbol;
		private String executive;
		private String executiveTitle;
		private String securityType;
		private TransactionType transactionType; //acquisition_or_disposal;
		private BigDecimal shares;
		private BigDecimal sharePrice;
		
		public Builder(UUID id) {
			setId(id);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id for insiders transaction must be provided");
			this.id = id;
		}
		
		public Builder setTransactionDate(String transactionDate) throws ParseException {
			AssertionConcern.assertNotNull(transactionDate, "Transaction date must be provided");
			this.transactionDate = TranslationConcern.getDateFrom(transactionDate);
			return this;
		}
		
		public Builder setTicekrSymbok(String symbol) {
			AssertionConcern.assertNotNull(symbol, "Ticker symbol must be provided");
			this.tickerSymbol = symbol;
			return this;
		}
		
		public Builder setExecutive(String executive) {
			AssertionConcern.assertNotEmpty(executive, "Executive name must be provided");
			this.executive = executive;
			return this;
		}
		
		public Builder setExecutiveTitle(String executiveTitle) {
			AssertionConcern.assertNotEmpty(executiveTitle, "Executive title must be provided");
			this.executiveTitle = executiveTitle;
			return this;
		}
		
		public Builder setSecutityType(String securityType) {
			this.securityType = securityType;
			return this;
		}
		
		public Builder setTransactionType(String transactionType) {
			AssertionConcern.assertNotNull(transactionType, "Transaction type must be provided");
			this.transactionType = TransactionType.get(transactionType);
			return this;
		}
		
		public Builder setShares(String shares) {
			this.shares = TranslationConcern.getNumberFrom(shares);
			return this;
		}
		
		public Builder setSharePrice(String sharePrice) {
			this.sharePrice = TranslationConcern.getNumberFrom(sharePrice);
			return this;
		}
		
		public InsiderTransaction build() {
			return new InsiderTransaction(this);
		}
	}
	
}
