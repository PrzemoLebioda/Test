package com.comida.sia.fundamentals.domain.model.cashflow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity(name = "CashFlowReport")
@Table(name = "fundamentals_cash_flow_reports",
indexes = {
		@Index(name = "cash_flow_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "cash_flow_reports_type_idx", columnList = "type"),
		@Index(name = "cash_flow_reports_fiscal_date_ending_idx", columnList = "fiscal_date_ending")
})
public class CashFlowReport implements ValueObject<CashFlowReport>, Comparable<CashFlowReport>, TimeSeries {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873103319112469235L;

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
	
	@Column(name = "reported_currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private CurrencySymbol reportedCurrency;
	
	@Column(name = "operating_cash_flow", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal operatingCashflow;
	
	@Column(name = "payments_for_operating_activities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal paymentsForOperatingActivities;
	
	@Column(name = "proceeds_from_operating_activities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromOperatingActivities;
	
	@Column(name = "change_in_operating_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInOperatingLiabilities;
	
	@Column(name = "change_in_operating_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInOperatingAssets;
	
	@Column(name = "depreciation_depletion_and_amortization", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal depreciationDepletionAndAmortization;
	
	@Column(name = "capital_expenditures", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal capitalExpenditures;
	
	@Column(name = "change_in_receivables", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInReceivables;
	
	@Column(name = "change_in_inventory", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInInventory;
	
	@Column(name = "profit_loss", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal profitLoss;
	
	@Column(name = "cash_flow_from_investment", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal cashflowFromInvestment;
	
	@Column(name = "cash_flow_from_financing", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal cashflowFromFinancing;
	
	@Column(name = "proceeds_from_repayments_of_short_term_debt", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromRepaymentsOfShortTermDebt;
	
	@Column(name = "payments_for_repurchase_of_common_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal paymentsForRepurchaseOfCommonStock;
	
	@Column(name = "payments_for_repurchase_of_equity", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal paymentsForRepurchaseOfEquity;
	
	@Column(name = "payments_for_repurchase_of_preferred_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal paymentsForRepurchaseOfPreferredStock;
	
	@Column(name = "dividend_payout", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal dividendPayout;
	
	@Column(name = "dividend_payout_common_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal dividendPayoutCommonStock;
	
	@Column(name = "dividend_payout_preferred_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal dividendPayoutPreferredStock;
	
	@Column(name = "proceeds_from_issuance_of_common_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromIssuanceOfCommonStock;
	
	@Column(name = "proceeds_from_long_term_debt_and_capital_securities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
	
	@Column(name = "proceeds_from_issuance_of_preferred_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromIssuanceOfPreferredStock;
	
	@Column(name = "proceeds_from_repurchase_of_equity", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromRepurchaseOfEquity;
	
	@Column(name = "proceeds_from_sale_of_treasury_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal proceedsFromSaleOfTreasuryStock;
	
	@Column(name = "change_in_cash_and_cash_equivalents", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInCashAndCashEquivalents;
	
	@Column(name = "change_in_exchange_rate", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal changeInExchangeRate;
	
	@Column(name = "net_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal netIncome;
	
	public CashFlowReport() {
		super();
	}
	
	private CashFlowReport(Builder builder) {
		id = builder.id;
		tickerSymbol = builder.tickerSymbol;
		type = builder.type;
		fiscalDateEnding = builder.fiscalDateEnding;
		reportedCurrency = builder.reportedCurrency;
		operatingCashflow = builder.operatingCashflow;
		paymentsForOperatingActivities  = builder.paymentsForOperatingActivities;
		proceedsFromOperatingActivities = builder.proceedsFromOperatingActivities;
		changeInOperatingLiabilities = builder.changeInOperatingLiabilities;
		changeInOperatingAssets = builder.changeInOperatingAssets;
		depreciationDepletionAndAmortization = builder.depreciationDepletionAndAmortization;
		capitalExpenditures = builder.capitalExpenditures;
		changeInReceivables = builder.changeInReceivables;
		changeInInventory = builder.changeInInventory;
		profitLoss = builder.profitLoss;
		cashflowFromInvestment = builder.cashflowFromInvestment;
		cashflowFromFinancing = builder.cashflowFromFinancing;
		proceedsFromRepaymentsOfShortTermDebt = builder.proceedsFromRepaymentsOfShortTermDebt;
		paymentsForRepurchaseOfCommonStock = builder.paymentsForRepurchaseOfCommonStock;
		paymentsForRepurchaseOfEquity = builder.paymentsForRepurchaseOfEquity;
		paymentsForRepurchaseOfPreferredStock = builder.paymentsForRepurchaseOfPreferredStock;
		dividendPayout = builder.dividendPayout;
		dividendPayoutCommonStock = builder.dividendPayoutCommonStock;
		dividendPayoutPreferredStock = builder.dividendPayoutPreferredStock;
		proceedsFromIssuanceOfCommonStock = builder.proceedsFromIssuanceOfCommonStock;
		proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = builder.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
		proceedsFromIssuanceOfPreferredStock = builder.proceedsFromIssuanceOfPreferredStock;
		proceedsFromRepurchaseOfEquity = builder.proceedsFromRepurchaseOfEquity;
		proceedsFromSaleOfTreasuryStock = builder.proceedsFromSaleOfTreasuryStock;
		changeInCashAndCashEquivalents = builder.changeInCashAndCashEquivalents;
		changeInExchangeRate = builder.changeInExchangeRate;
		netIncome = builder.netIncome;
	}
	
	@Override
	public Date occuranceTime() {
		return fiscalDateEnding;
	}
	
	@Override
	public int compareTo(CashFlowReport o) {
		if (this.fiscalDateEnding == null || o.fiscalDateEnding == null) {
			return 0;
		} else {
			return this.fiscalDateEnding.compareTo(o.fiscalDateEnding);
		}
	}
	
	@Override
	public boolean sameValueAs(CashFlowReport other) {
		if(other != null) {
			return  (ComparationConcern.sameDates(this.fiscalDateEnding, other.fiscalDateEnding)) &&
					((this.reportedCurrency == null && other.reportedCurrency == null) || (this.reportedCurrency != null && this.reportedCurrency.equals(other.reportedCurrency))) &&
					((this.operatingCashflow == null && other.operatingCashflow == null) || (this.operatingCashflow != null && this.operatingCashflow.compareTo(other.operatingCashflow) == 0)) &&
					((this.paymentsForOperatingActivities == null && other.paymentsForOperatingActivities == null) || (this.paymentsForOperatingActivities != null && this.paymentsForOperatingActivities.compareTo(other.paymentsForOperatingActivities) == 0)) &&
					((this.proceedsFromOperatingActivities == null && other.proceedsFromOperatingActivities == null) || (this.proceedsFromOperatingActivities != null && this.proceedsFromOperatingActivities.compareTo(other.proceedsFromOperatingActivities) == 0)) &&
					((this.changeInOperatingLiabilities == null && other.changeInOperatingLiabilities == null) || (this.changeInOperatingLiabilities != null && this.changeInOperatingLiabilities.compareTo(other.changeInOperatingLiabilities) == 0)) &&
					((this.changeInOperatingAssets == null && other.changeInOperatingAssets == null) || (this.changeInOperatingAssets != null && this.changeInOperatingAssets.compareTo(other.changeInOperatingAssets) == 0)) &&
					((this.depreciationDepletionAndAmortization == null && other.depreciationDepletionAndAmortization == null) || (this.depreciationDepletionAndAmortization != null && this.depreciationDepletionAndAmortization.compareTo(other.depreciationDepletionAndAmortization) == 0)) &&
					((this.capitalExpenditures == null && other.capitalExpenditures == null) || (this.capitalExpenditures != null && this.capitalExpenditures.compareTo(other.capitalExpenditures) == 0)) &&
					((this.changeInReceivables == null && other.changeInReceivables == null) || (this.changeInReceivables != null && this.changeInReceivables.compareTo(other.changeInReceivables) == 0)) &&
					((this.changeInInventory == null && other.changeInInventory == null) || (this.changeInInventory != null && this.changeInInventory.compareTo(other.changeInInventory) == 0)) &&
					((this.profitLoss == null && other.profitLoss == null) || (this.profitLoss != null && this.profitLoss.compareTo(other.profitLoss) == 0)) &&
					((this.cashflowFromInvestment == null && other.cashflowFromInvestment == null) || (this.cashflowFromInvestment != null && this.cashflowFromInvestment.compareTo(other.cashflowFromInvestment) == 0)) &&
					((this.cashflowFromFinancing == null && other.cashflowFromFinancing == null) || (this.cashflowFromFinancing != null && this.cashflowFromFinancing.compareTo(other.cashflowFromFinancing) == 0)) &&
					((this.proceedsFromRepaymentsOfShortTermDebt == null && other.proceedsFromRepaymentsOfShortTermDebt == null) || (this.proceedsFromRepaymentsOfShortTermDebt != null && this.proceedsFromRepaymentsOfShortTermDebt.compareTo(other.proceedsFromRepaymentsOfShortTermDebt) == 0)) &&
					((this.paymentsForRepurchaseOfCommonStock == null && other.paymentsForRepurchaseOfCommonStock == null) || (this.paymentsForRepurchaseOfCommonStock != null && this.paymentsForRepurchaseOfCommonStock.compareTo(other.paymentsForRepurchaseOfCommonStock) == 0)) &&
					((this.paymentsForRepurchaseOfEquity == null && other.paymentsForRepurchaseOfEquity == null) || (this.paymentsForRepurchaseOfEquity != null && this.paymentsForRepurchaseOfEquity.compareTo(other.paymentsForRepurchaseOfEquity) == 0)) &&
					((this.paymentsForRepurchaseOfPreferredStock == null && other.paymentsForRepurchaseOfPreferredStock == null) || (this.paymentsForRepurchaseOfPreferredStock != null && this.paymentsForRepurchaseOfPreferredStock.compareTo(other.paymentsForRepurchaseOfPreferredStock) == 0)) &&
					((this.dividendPayout == null && other.dividendPayout == null) || (this.dividendPayout != null && this.dividendPayout.compareTo(other.dividendPayout) == 0)) &&
					((this.dividendPayoutCommonStock == null && other.dividendPayoutCommonStock == null) || (this.dividendPayoutCommonStock != null && this.dividendPayoutCommonStock.compareTo(other.dividendPayoutCommonStock) == 0)) &&
					((this.dividendPayoutPreferredStock == null && other.dividendPayoutPreferredStock == null) || (this.dividendPayoutPreferredStock != null && this.dividendPayoutPreferredStock.compareTo(other.dividendPayoutPreferredStock) == 0)) &&
					((this.proceedsFromIssuanceOfCommonStock == null && other.proceedsFromIssuanceOfCommonStock == null) || (this.proceedsFromIssuanceOfCommonStock != null && this.proceedsFromIssuanceOfCommonStock.compareTo(other.proceedsFromIssuanceOfCommonStock) == 0)) &&
					((this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet == null && other.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet == null) || (this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet != null && this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet.compareTo(other.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet) == 0)) &&
					((this.proceedsFromIssuanceOfPreferredStock == null && other.proceedsFromIssuanceOfPreferredStock == null) || (this.proceedsFromIssuanceOfPreferredStock != null && this.proceedsFromIssuanceOfPreferredStock.compareTo(other.proceedsFromIssuanceOfPreferredStock) == 0)) &&
					((this.proceedsFromRepurchaseOfEquity == null && other.proceedsFromRepurchaseOfEquity == null) || (this.proceedsFromRepurchaseOfEquity != null && this.proceedsFromRepurchaseOfEquity.compareTo(other.proceedsFromRepurchaseOfEquity) == 0)) &&
					((this.proceedsFromSaleOfTreasuryStock == null && other.proceedsFromSaleOfTreasuryStock == null) || (this.proceedsFromSaleOfTreasuryStock != null && this.proceedsFromSaleOfTreasuryStock.compareTo(other.proceedsFromSaleOfTreasuryStock) == 0)) &&
					((this.changeInCashAndCashEquivalents == null && other.changeInCashAndCashEquivalents == null) || (this.changeInCashAndCashEquivalents != null && this.changeInCashAndCashEquivalents.compareTo(other.changeInCashAndCashEquivalents) == 0)) &&
					((this.changeInExchangeRate == null && other.changeInExchangeRate == null) || (this.changeInExchangeRate != null && this.changeInExchangeRate.compareTo(other.changeInExchangeRate) == 0)) &&
					((this.netIncome == null && other.netIncome == null) || (this.netIncome != null && this.netIncome.compareTo(other.netIncome) == 0));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(capitalExpenditures, cashflowFromFinancing, cashflowFromInvestment,
				changeInCashAndCashEquivalents, changeInExchangeRate, changeInInventory, changeInOperatingAssets,
				changeInOperatingLiabilities, changeInReceivables, depreciationDepletionAndAmortization, dividendPayout,
				dividendPayoutCommonStock, dividendPayoutPreferredStock, fiscalDateEnding, netIncome, operatingCashflow,
				paymentsForOperatingActivities, paymentsForRepurchaseOfCommonStock, paymentsForRepurchaseOfEquity,
				paymentsForRepurchaseOfPreferredStock, proceedsFromIssuanceOfCommonStock,
				proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet, proceedsFromIssuanceOfPreferredStock,
				proceedsFromOperatingActivities, proceedsFromRepaymentsOfShortTermDebt, proceedsFromRepurchaseOfEquity,
				proceedsFromSaleOfTreasuryStock, profitLoss, reportedCurrency);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashFlowReport other = (CashFlowReport) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "CashFlow [fiscalDateEnding=" + fiscalDateEnding
				+ ", reportedCurrency=" + reportedCurrency + ", operatingCashflow=" + operatingCashflow
				+ ", paymentsForOperatingActivities=" + paymentsForOperatingActivities
				+ ", proceedsFromOperatingActivities=" + proceedsFromOperatingActivities
				+ ", changeInOperatingLiabilities=" + changeInOperatingLiabilities + ", changeInOperatingAssets="
				+ changeInOperatingAssets + ", depreciationDepletionAndAmortization="
				+ depreciationDepletionAndAmortization + ", capitalExpenditures=" + capitalExpenditures
				+ ", changeInReceivables=" + changeInReceivables + ", changeInInventory=" + changeInInventory
				+ ", profitLoss=" + profitLoss + ", cashflowFromInvestment=" + cashflowFromInvestment
				+ ", cashflowFromFinancing=" + cashflowFromFinancing + ", proceedsFromRepaymentsOfShortTermDebt="
				+ proceedsFromRepaymentsOfShortTermDebt + ", paymentsForRepurchaseOfCommonStock="
				+ paymentsForRepurchaseOfCommonStock + ", paymentsForRepurchaseOfEquity="
				+ paymentsForRepurchaseOfEquity + ", paymentsForRepurchaseOfPreferredStock="
				+ paymentsForRepurchaseOfPreferredStock + ", dividendPayout=" + dividendPayout
				+ ", dividendPayoutCommonStock=" + dividendPayoutCommonStock + ", dividendPayoutPreferredStock="
				+ dividendPayoutPreferredStock + ", proceedsFromIssuanceOfCommonStock="
				+ proceedsFromIssuanceOfCommonStock + ", proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet="
				+ proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet + ", proceedsFromIssuanceOfPreferredStock="
				+ proceedsFromIssuanceOfPreferredStock + ", proceedsFromRepurchaseOfEquity="
				+ proceedsFromRepurchaseOfEquity + ", proceedsFromSaleOfTreasuryStock="
				+ proceedsFromSaleOfTreasuryStock + ", changeInCashAndCashEquivalents=" + changeInCashAndCashEquivalents
				+ ", changeInExchangeRate=" + changeInExchangeRate + ", netIncome=" + netIncome + "]";
	}
	
	
	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private ReportType type;
		private Date fiscalDateEnding;
		private CurrencySymbol reportedCurrency;
		private BigDecimal operatingCashflow;
		private BigDecimal paymentsForOperatingActivities;
		private BigDecimal proceedsFromOperatingActivities;
		private BigDecimal changeInOperatingLiabilities;
		private BigDecimal changeInOperatingAssets;
		private BigDecimal depreciationDepletionAndAmortization;
		private BigDecimal capitalExpenditures;
		private BigDecimal changeInReceivables;
		private BigDecimal changeInInventory;
		private BigDecimal profitLoss;
		private BigDecimal cashflowFromInvestment;
		private BigDecimal cashflowFromFinancing;
		private BigDecimal proceedsFromRepaymentsOfShortTermDebt;
		private BigDecimal paymentsForRepurchaseOfCommonStock;
		private BigDecimal paymentsForRepurchaseOfEquity;
		private BigDecimal paymentsForRepurchaseOfPreferredStock;
		private BigDecimal dividendPayout;
		private BigDecimal dividendPayoutCommonStock;
		private BigDecimal dividendPayoutPreferredStock;
		private BigDecimal proceedsFromIssuanceOfCommonStock;
		private BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
		private BigDecimal proceedsFromIssuanceOfPreferredStock;
		private BigDecimal proceedsFromRepurchaseOfEquity;
		private BigDecimal proceedsFromSaleOfTreasuryStock;
		private BigDecimal changeInCashAndCashEquivalents;
		private BigDecimal changeInExchangeRate;
		private BigDecimal netIncome;
		
		public Builder(UUID id, String tickerSymbol, ReportType type) {
			setId(id);
			setTickerSymol(tickerSymbol);
			setType(type);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id must be provided for cash flow quarter report");
			this.id = id;
		}
		
		private void setTickerSymol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol must be provided");
			this.tickerSymbol = tickerSymbol;
		}
		
		private void setType(ReportType type) {
			AssertionConcern.assertNotNull(type, "Type of cash flow must be provided");
			this.type = type;
		}
		
		public Builder fiscalDateEnding(Date fiscalDateEnding) {
			AssertionConcern.assertNotNull(fiscalDateEnding, "Fiscal date ending must be provided for cash flow quarter");
			this.fiscalDateEnding = fiscalDateEnding;
			return this;
		}
		
		public Builder reportedCurrency(CurrencySymbol reportedCurrency){
			AssertionConcern.assertNotNull(reportedCurrency, "Reported currency must be provided for cash flow report");
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		
		public Builder operatingCashflow(BigDecimal operatingCashflow){
			this.operatingCashflow = operatingCashflow;
			return this;
		}
		
		public Builder paymentsForOperatingActivities(BigDecimal paymentsForOperatingActivities){
			this.paymentsForOperatingActivities = paymentsForOperatingActivities;
			return this;
		}
		
		public Builder proceedsFromOperatingActivities(BigDecimal proceedsFromOperatingActivities){
			this.proceedsFromOperatingActivities = proceedsFromOperatingActivities;
			return this;
		}
		
		public Builder changeInOperatingLiabilities(BigDecimal changeInOperatingLiabilities){
			this.changeInOperatingLiabilities = changeInOperatingLiabilities;
			return this;
		}
		
		public Builder changeInOperatingAssets(BigDecimal changeInOperatingAssets){
			this.changeInOperatingAssets = changeInOperatingAssets;
			return this;
		}
		
		public Builder depreciationDepletionAndAmortization(BigDecimal depreciationDepletionAndAmortization){
			this.depreciationDepletionAndAmortization = depreciationDepletionAndAmortization;
			return this;
		}
		
		public Builder capitalExpenditures(BigDecimal capitalExpenditures){
			this.capitalExpenditures = capitalExpenditures;
			return this;
		}
		
		public Builder changeInReceivables(BigDecimal changeInReceivables){
			this.changeInReceivables = changeInReceivables;
			return this;
		}
		
		protected Builder changeInInventory(BigDecimal changeInInventory){
			this.changeInInventory = changeInInventory;
			return this;
		}
		
		public Builder profitLoss(BigDecimal profitLoss){
			this.profitLoss = profitLoss;
			return this;
		}
		
		public Builder cashflowFromInvestment(BigDecimal cashflowFromInvestment){
			this.cashflowFromInvestment = cashflowFromInvestment;
			return this;
		}
		
		public Builder cashflowFromFinancing(BigDecimal cashflowFromFinancing){
			this.cashflowFromFinancing = cashflowFromFinancing;
			return this;
		}
		
		public Builder proceedsFromRepaymentsOfShortTermDebt(BigDecimal proceedsFromRepaymentsOfShortTermDebt){
			this.proceedsFromRepaymentsOfShortTermDebt = proceedsFromRepaymentsOfShortTermDebt;
			return this;
		}
		
		public Builder paymentsForRepurchaseOfCommonStock(BigDecimal paymentsForRepurchaseOfCommonStock){
			this.paymentsForRepurchaseOfCommonStock = paymentsForRepurchaseOfCommonStock;
			return this;
		}
		
		public Builder paymentsForRepurchaseOfEquity(BigDecimal paymentsForRepurchaseOfEquity){
			this.paymentsForRepurchaseOfEquity = paymentsForRepurchaseOfEquity;
			return this;
		}
		
		public Builder paymentsForRepurchaseOfPreferredStock(BigDecimal paymentsForRepurchaseOfPreferredStock){
			this.paymentsForRepurchaseOfPreferredStock = paymentsForRepurchaseOfPreferredStock;
			return this;
		}
		
		public Builder dividendPayout(BigDecimal dividendPayout){
			this.dividendPayout = dividendPayout;
			return this;
		}
		
		public Builder dividendPayoutCommonStock(BigDecimal dividendPayoutCommonStock){
			this.dividendPayoutCommonStock = dividendPayoutCommonStock;
			return this;
		}
		
		public Builder dividendPayoutPreferredStock(BigDecimal dividendPayoutPreferredStock){
			this.dividendPayoutPreferredStock = dividendPayoutPreferredStock;
			return this;
		}
		
		public Builder proceedsFromIssuanceOfCommonStock(BigDecimal proceedsFromIssuanceOfCommonStock){
			this.proceedsFromIssuanceOfCommonStock = proceedsFromIssuanceOfCommonStock;
			return this;
		}
		
		public Builder proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet(BigDecimal proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet){
			this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
			return this;
		}
		
		public Builder proceedsFromIssuanceOfPreferredStock(BigDecimal proceedsFromIssuanceOfPreferredStock){
			this.proceedsFromIssuanceOfPreferredStock = proceedsFromIssuanceOfPreferredStock;
			return this;
		}
		
		public Builder proceedsFromRepurchaseOfEquity(BigDecimal proceedsFromRepurchaseOfEquity){
			this.proceedsFromRepurchaseOfEquity = proceedsFromRepurchaseOfEquity;
			return this;
		}
		
		public Builder proceedsFromSaleOfTreasuryStock(BigDecimal proceedsFromSaleOfTreasuryStock){
			this.proceedsFromSaleOfTreasuryStock = proceedsFromSaleOfTreasuryStock;
			return this;
		}
		
		public Builder changeInCashAndCashEquivalents(BigDecimal changeInCashAndCashEquivalents){
			this.changeInCashAndCashEquivalents = changeInCashAndCashEquivalents;
			return this;
		}
		
		public Builder changeInExchangeRate(BigDecimal changeInExchangeRate){
			this.changeInExchangeRate = changeInExchangeRate;
			return this;
		}
		
		public Builder netIncome(BigDecimal netIncome){
			this.netIncome = netIncome;
			return this;
		}
		
		public CashFlowReport build() {
			return new CashFlowReport(this);
		}
	}

}
