package com.comida.sia.fundamentals.domain.model.balancesheet;

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
import lombok.Getter;

@Entity(name = "BalanceSheetReport")
@Table(name = "fundamentals_balance_sheet_reports",
	indexes = {
		@Index(name = "balance_sheet_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "balance_sheet_reports_type_idx", columnList = "type"),
		@Index(name = "balance_sheet_reports_fiscal_date_ending_idx", columnList = "fiscal_date_ending")
})
@Getter
public class BalanceSheetReport implements ValueObject<BalanceSheetReport>, Comparable<BalanceSheetReport>, TimeSeries {

	/**
	 * 
	 */
	private static final long serialVersionUID = -322857523731013490L;
	
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
	
	@Column(name = "total_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalAssets;
	
	@Column(name = "total_currentAssets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalCurrentAssets;
	
	@Column(name = "cash_and_cash_equivalents_at_carrying_value", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal cashAndCashEquivalentsAtCarryingValue;
	
	@Column(name = "cash_and_short_term_investments", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal cashAndShortTermInvestments;
	
	@Column(name = "inventory", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal inventory;
	
	@Column(name = "current_net_receivables", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal currentNetReceivables;
	
	@Column(name = "total_non_current_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalNonCurrentAssets;
	
	@Column(name = "property_plant_equipment", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal propertyPlantEquipment;
	
	@Column(name = "accumulated_depreciation_amortization_ppe", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal accumulatedDepreciationAmortizationPPE;
	
	@Column(name = "intangible_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal intangibleAssets;
	
	@Column(name = "intangible_assets_excluding_good_will", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal intangibleAssetsExcludingGoodwill;
	
	@Column(name = "goodwill", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal goodwill;
	
	@Column(name = "investments", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal investments;
	
	@Column(name = "long_term_ivestments", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal longTermInvestments;
	
	@Column(name = "short_term_investments", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal shortTermInvestments;
	
	@Column(name = "other_current_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal otherCurrentAssets;
	
	@Column(name = "other_non_current_assets", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal otherNonCurrentAssets;
	
	@Column(name = "total_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalLiabilities;
	
	@Column(name = "total_current_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalCurrentLiabilities;
	
	@Column(name = "current_accounts_payable", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal currentAccountsPayable;
	
	@Column(name = "deferred_revenue", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal deferredRevenue;
	
	@Column(name = "current_debt", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal currentDebt;
	
	@Column(name = "short_term_debt", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal shortTermDebt;
	
	@Column(name = "total_non_current_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalNonCurrentLiabilities;
	
	@Column(name = "capital_lease_obligations", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal capitalLeaseObligations;
	
	@Column(name = "long_term_debt", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal longTermDebt;
	
	@Column(name = "current_long_term_debt", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal currentLongTermDebt;
	
	@Column(name = "long_term_debt_noncurrent", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal longTermDebtNoncurrent;
	
	@Column(name = "short_long_term_debt_total", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal shortLongTermDebtTotal;
	
	@Column(name = "other_current_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal otherCurrentLiabilities;
	
	@Column(name = "other_non_current_liabilities", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal otherNonCurrentLiabilities;
	
	@Column(name = "total_shareholder_equity", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalShareholderEquity;
	
	@Column(name = "treasury_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal treasuryStock;
	
	@Column(name = "retained_earnings", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal retainedEarnings;
	
	@Column(name = "common_stock", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal commonStock;
	
	@Column(name = "common_stock_shares_outstanding", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal commonStockSharesOutstanding;
	
	public BalanceSheetReport() {
		super();
	}
	
	private BalanceSheetReport(Builder builder) {
		super();
		
		this.id = builder.id;
		this.tickerSymbol = builder.tickerSymbol;
		this.type = builder.type;
		this.fiscalDateEnding = builder.fiscalDateEnding;
		this.reportedCurrency = builder.reportedCurrency;
		this.totalAssets = builder.totalAssets;
		this.totalCurrentAssets = builder.totalCurrentAssets;
		this.cashAndCashEquivalentsAtCarryingValue = builder.cashAndCashEquivalentsAtCarryingValue;
		this.cashAndShortTermInvestments = builder.cashAndShortTermInvestments;
		this.inventory = builder.inventory;
		this.currentNetReceivables = builder.currentNetReceivables;
		this.totalNonCurrentAssets = builder.totalNonCurrentAssets;
		this.propertyPlantEquipment = builder.propertyPlantEquipment;
		this.accumulatedDepreciationAmortizationPPE = builder.accumulatedDepreciationAmortizationPPE;
		this.intangibleAssets = builder.intangibleAssets;
		this.intangibleAssetsExcludingGoodwill = builder.intangibleAssetsExcludingGoodwill;
		this.goodwill = builder.goodwill;
		this.investments = builder.investments;
		this.longTermInvestments = builder.longTermInvestments;
		this.shortTermInvestments = builder.shortTermInvestments;
		this.otherCurrentAssets = builder.otherCurrentAssets;
		this.otherNonCurrentAssets = builder.otherNonCurrentAssets;
		this.totalLiabilities = builder.totalLiabilities;
		this.totalCurrentLiabilities = builder.totalCurrentLiabilities;
		this.currentAccountsPayable = builder.currentAccountsPayable;
		this.deferredRevenue = builder.deferredRevenue;
		this.currentDebt = builder.currentDebt;
		this.shortTermDebt = builder.shortTermDebt;
		this.totalNonCurrentLiabilities = builder.totalNonCurrentLiabilities;
		this.capitalLeaseObligations = builder.capitalLeaseObligations;
		this.longTermDebt = builder.longTermDebt;
		this.currentLongTermDebt = builder.currentLongTermDebt;
		this.longTermDebtNoncurrent = builder.longTermDebtNoncurrent;
		this.shortLongTermDebtTotal = builder.shortLongTermDebtTotal;
		this.otherCurrentLiabilities = builder.otherCurrentLiabilities;
		this.otherNonCurrentLiabilities = builder.otherNonCurrentLiabilities;
		this.totalShareholderEquity = builder.totalShareholderEquity;
		this.treasuryStock = builder.treasuryStock;
		this.retainedEarnings = builder.retainedEarnings;
		this.commonStock = builder.commonStock;
		this.commonStockSharesOutstanding = builder.commonStockSharesOutstanding;
	}
	
	@Override
	public Date occuranceTime() {
		return fiscalDateEnding;
	}
	
	@Override
	public int compareTo(BalanceSheetReport o) {
		if (this.fiscalDateEnding == null || o.fiscalDateEnding == null) {
			return 0;
		} else {
			return this.fiscalDateEnding.compareTo(o.fiscalDateEnding);
		}
	}

	@Override
	public boolean sameValueAs(BalanceSheetReport other) {
		if(other != null) {
			return  (this.type.equals(other.type)) &&
					(this.tickerSymbol.equals(other.tickerSymbol)) &&
					(ComparationConcern.sameDates(this.fiscalDateEnding, other.fiscalDateEnding)) &&
					(this.reportedCurrency != null && this.reportedCurrency.equals(other.reportedCurrency)) &&
					(ComparationConcern.sameNumbers(totalAssets, other.totalAssets)) &&
					(ComparationConcern.sameNumbers(totalCurrentAssets, other.totalCurrentAssets)) &&
					(ComparationConcern.sameNumbers(cashAndCashEquivalentsAtCarryingValue, other.cashAndCashEquivalentsAtCarryingValue)) &&
					(ComparationConcern.sameNumbers(cashAndShortTermInvestments, other.cashAndShortTermInvestments)) &&
					(ComparationConcern.sameNumbers(inventory, other.inventory)) &&
					(ComparationConcern.sameNumbers(currentNetReceivables, other.currentNetReceivables)) &&
					(ComparationConcern.sameNumbers(totalNonCurrentAssets, other.totalNonCurrentAssets)) &&
					(ComparationConcern.sameNumbers(propertyPlantEquipment, other.propertyPlantEquipment)) &&
					(ComparationConcern.sameNumbers(accumulatedDepreciationAmortizationPPE, other.accumulatedDepreciationAmortizationPPE)) &&
					(ComparationConcern.sameNumbers(intangibleAssets, other.intangibleAssets)) &&
					(ComparationConcern.sameNumbers(intangibleAssetsExcludingGoodwill, other.intangibleAssetsExcludingGoodwill)) &&
					(ComparationConcern.sameNumbers(goodwill, other.goodwill)) &&
					(ComparationConcern.sameNumbers(investments, other.investments)) &&
					(ComparationConcern.sameNumbers(longTermInvestments, other.longTermInvestments)) &&
					(ComparationConcern.sameNumbers(shortTermInvestments, other.shortTermInvestments)) &&
					(ComparationConcern.sameNumbers(otherCurrentAssets, other.otherCurrentAssets)) &&
					(ComparationConcern.sameNumbers(otherNonCurrentAssets, other.otherNonCurrentAssets)) &&
					(ComparationConcern.sameNumbers(totalLiabilities, other.totalLiabilities)) &&
					(ComparationConcern.sameNumbers(totalCurrentLiabilities, other.totalCurrentLiabilities)) &&
					(ComparationConcern.sameNumbers(currentAccountsPayable, other.currentAccountsPayable)) &&
					(ComparationConcern.sameNumbers(deferredRevenue, other.deferredRevenue)) &&
					(ComparationConcern.sameNumbers(currentDebt, other.currentDebt)) &&
					(ComparationConcern.sameNumbers(shortTermDebt, other.shortTermDebt)) &&
					(ComparationConcern.sameNumbers(totalNonCurrentLiabilities, other.totalNonCurrentLiabilities)) &&
					(ComparationConcern.sameNumbers(capitalLeaseObligations, other.capitalLeaseObligations)) &&
					(ComparationConcern.sameNumbers(longTermDebt, other.longTermDebt)) &&
					(ComparationConcern.sameNumbers(currentLongTermDebt, other.currentLongTermDebt)) &&
					(ComparationConcern.sameNumbers(longTermDebtNoncurrent, other.longTermDebtNoncurrent)) &&
					(ComparationConcern.sameNumbers(shortLongTermDebtTotal, other.shortLongTermDebtTotal)) &&
					(ComparationConcern.sameNumbers(otherCurrentLiabilities, other.otherCurrentLiabilities)) &&
					(ComparationConcern.sameNumbers(otherNonCurrentLiabilities, other.otherNonCurrentLiabilities)) &&
					(ComparationConcern.sameNumbers(totalShareholderEquity, other.totalShareholderEquity)) &&
					(ComparationConcern.sameNumbers(treasuryStock, other.treasuryStock)) &&
					(ComparationConcern.sameNumbers(retainedEarnings, other.retainedEarnings)) &&
					(ComparationConcern.sameNumbers(commonStock, other.commonStock)) &&
					(ComparationConcern.sameNumbers(commonStockSharesOutstanding, other.commonStockSharesOutstanding));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(accumulatedDepreciationAmortizationPPE, capitalLeaseObligations,
				cashAndCashEquivalentsAtCarryingValue, cashAndShortTermInvestments, commonStock,
				commonStockSharesOutstanding, currentAccountsPayable, currentDebt, currentLongTermDebt,
				currentNetReceivables, deferredRevenue, fiscalDateEnding, goodwill, intangibleAssets,
				intangibleAssetsExcludingGoodwill, inventory, investments, longTermDebt, longTermDebtNoncurrent,
				longTermInvestments, otherCurrentAssets, otherCurrentLiabilities, otherNonCurrentAssets,
				otherNonCurrentLiabilities, propertyPlantEquipment, reportedCurrency, retainedEarnings,
				shortLongTermDebtTotal, shortTermDebt, shortTermInvestments, totalAssets, totalCurrentAssets,
				totalCurrentLiabilities, totalLiabilities, totalNonCurrentAssets, totalNonCurrentLiabilities,
				totalShareholderEquity, treasuryStock, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceSheetReport other = (BalanceSheetReport) obj;
		return this.sameValueAs(other);
	}

	@Override
	public String toString() {
		return "AnnualBalanceSheet [type=" + type + "fiscalDateEnding=" + fiscalDateEnding
				+ ", reportedCurrency=" + reportedCurrency + ", totalAssets=" + totalAssets + ", totalCurrentAssets="
				+ totalCurrentAssets + ", cashAndCashEquivalentsAtCarryingValue="
				+ cashAndCashEquivalentsAtCarryingValue + ", cashAndShortTermInvestments=" + cashAndShortTermInvestments
				+ ", inventory=" + inventory + ", currentNetReceivables=" + currentNetReceivables
				+ ", totalNonCurrentAssets=" + totalNonCurrentAssets + ", propertyPlantEquipment="
				+ propertyPlantEquipment + ", accumulatedDepreciationAmortizationPPE="
				+ accumulatedDepreciationAmortizationPPE + ", intangibleAssets=" + intangibleAssets
				+ ", intangibleAssetsExcludingGoodwill=" + intangibleAssetsExcludingGoodwill + ", goodwill=" + goodwill
				+ ", investments=" + investments + ", longTermInvestments=" + longTermInvestments
				+ ", shortTermInvestments=" + shortTermInvestments + ", otherCurrentAssets=" + otherCurrentAssets
				+ ", otherNonCurrentAssets=" + otherNonCurrentAssets + ", totalLiabilities=" + totalLiabilities
				+ ", totalCurrentLiabilities=" + totalCurrentLiabilities + ", currentAccountsPayable="
				+ currentAccountsPayable + ", deferredRevenue=" + deferredRevenue + ", currentDebt=" + currentDebt
				+ ", shortTermDebt=" + shortTermDebt + ", totalNonCurrentLiabilities=" + totalNonCurrentLiabilities
				+ ", capitalLeaseObligations=" + capitalLeaseObligations + ", longTermDebt=" + longTermDebt
				+ ", currentLongTermDebt=" + currentLongTermDebt + ", longTermDebtNoncurrent=" + longTermDebtNoncurrent
				+ ", shortLongTermDebtTotal=" + shortLongTermDebtTotal + ", otherCurrentLiabilities="
				+ otherCurrentLiabilities + ", otherNonCurrentLiabilities=" + otherNonCurrentLiabilities
				+ ", totalShareholderEquity=" + totalShareholderEquity + ", treasuryStock=" + treasuryStock
				+ ", retainedEarnings=" + retainedEarnings + ", commonStock=" + commonStock
				+ ", commonStockSharesOutstanding=" + commonStockSharesOutstanding + "]";
	}
	
	public static class Builder extends AssertionConcern {
		private UUID id;
		private String tickerSymbol;
		private ReportType type;
		private Date fiscalDateEnding;
		private CurrencySymbol reportedCurrency;
		private BigDecimal totalAssets;
		private BigDecimal totalCurrentAssets;
		private BigDecimal cashAndCashEquivalentsAtCarryingValue;
		private BigDecimal cashAndShortTermInvestments;
		private BigDecimal inventory;
		private BigDecimal currentNetReceivables;
		private BigDecimal totalNonCurrentAssets;
		private BigDecimal propertyPlantEquipment;
		private BigDecimal accumulatedDepreciationAmortizationPPE;
		private BigDecimal intangibleAssets;
		private BigDecimal intangibleAssetsExcludingGoodwill;
		private BigDecimal goodwill;
		private BigDecimal investments;
		private BigDecimal longTermInvestments;
		private BigDecimal shortTermInvestments;
		private BigDecimal otherCurrentAssets;
		private BigDecimal otherNonCurrentAssets;
		private BigDecimal totalLiabilities;
		private BigDecimal totalCurrentLiabilities;
		private BigDecimal currentAccountsPayable;
		private BigDecimal deferredRevenue;
		private BigDecimal currentDebt;
		private BigDecimal shortTermDebt;
		private BigDecimal totalNonCurrentLiabilities;
		private BigDecimal capitalLeaseObligations;
		private BigDecimal longTermDebt;
		private BigDecimal currentLongTermDebt;
		private BigDecimal longTermDebtNoncurrent;
		private BigDecimal shortLongTermDebtTotal;
		private BigDecimal otherCurrentLiabilities;
		private BigDecimal otherNonCurrentLiabilities;
		private BigDecimal totalShareholderEquity;
		private BigDecimal treasuryStock;
		private BigDecimal retainedEarnings;
		private BigDecimal commonStock;
		private BigDecimal commonStockSharesOutstanding;
		
		public Builder(UUID id, String tickerSymbol, ReportType type) {
			setId(id);
			setTickerSymbol(tickerSymbol);
			setReportType(type);
		}
		
		private void setId(UUID id) {
			assertNotNull(id, "Id of balance sheet report must be provided");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			assertNotNull(tickerSymbol, "Ticker symbol must be provided in orfer to register balance sheet report");
			this.tickerSymbol = tickerSymbol;
		}
		
		private void setReportType(ReportType reportType) {
			assertNotNull(reportType, "Report type of balance sheet must be provided");
			this.type = reportType;
		}
		
		public Builder fiscalDateEnding(Date fiscalDateEnding){
			assertNotNull(fiscalDateEnding, "Fiscal date ending must be provided for balance sheet report");
			this.fiscalDateEnding = fiscalDateEnding;
			return this;
		}
		
		public Builder reportedCurrency(CurrencySymbol reportedCurrency){
			assertNotNull(reportedCurrency, "Reported currency must be provided for balance sheet report");
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		
		public Builder totalAssets(BigDecimal totalAssets){
			this.totalAssets = totalAssets;
			return this;
		}
		
		public Builder totalCurrentAssets(BigDecimal totalCurrentAssets){
			this.totalCurrentAssets = totalCurrentAssets;
			return this;
		}
		
		public Builder cashAndCashEquivalentsAtCarryingValue(BigDecimal cashAndCashEquivalentsAtCarryingValue){
			this.cashAndCashEquivalentsAtCarryingValue = cashAndCashEquivalentsAtCarryingValue;
			return this;
		}
		
		public Builder cashAndShortTermInvestments(BigDecimal cashAndShortTermInvestments){
			this.cashAndShortTermInvestments = cashAndShortTermInvestments;
			return this;
		}
		
		public Builder inventory(BigDecimal inventory){
			this.inventory = inventory;
			return this;
		}
		
		public Builder currentNetReceivables(BigDecimal currentNetReceivables){
			this.currentNetReceivables = currentNetReceivables;
			return this;
		}
		
		public Builder totalNonCurrentAssets(BigDecimal totalNonCurrentAssets){
			this.totalNonCurrentAssets = totalNonCurrentAssets;
			return this;
		}
		
		public Builder propertyPlantEquipment(BigDecimal propertyPlantEquipment){
			this.propertyPlantEquipment = propertyPlantEquipment;
			return this;
		}
		
		public Builder accumulatedDepreciationAmortizationPPE(BigDecimal accumulatedDepreciationAmortizationPPE){
			this.accumulatedDepreciationAmortizationPPE = accumulatedDepreciationAmortizationPPE;
			return this;
		}
		
		public Builder intangibleAssets(BigDecimal intangibleAssets){
			this.intangibleAssets = intangibleAssets;
			return this;
		}
		
		public Builder intangibleAssetsExcludingGoodwill(BigDecimal intangibleAssetsExcludingGoodwill){
			this.intangibleAssetsExcludingGoodwill = intangibleAssetsExcludingGoodwill;
			return this;
		}
		
		public Builder goodwill(BigDecimal goodwill){
			this.goodwill = goodwill;
			return this;
		}
		
		public Builder investments(BigDecimal investments){
			this.investments = investments;
			return this;
		}
		
		public Builder longTermInvestments(BigDecimal longTermInvestments){
			this.longTermInvestments = longTermInvestments;
			return this;
		}
		
		public Builder shortTermInvestments(BigDecimal shortTermInvestments){
			this.shortTermInvestments = shortTermInvestments;
			return this;
		}
		
		public Builder otherCurrentAssets(BigDecimal otherCurrentAssets){
			this.otherCurrentAssets = otherCurrentAssets;
			return this;
		}
		
		public Builder otherNonCurrentAssets(BigDecimal otherNonCurrentAssets){
			this.otherNonCurrentAssets = otherNonCurrentAssets;
			return this;
		}
		
		public Builder totalLiabilities(BigDecimal totalLiabilities){
			this.totalLiabilities = totalLiabilities;
			return this;
		}
		
		public Builder totalCurrentLiabilities(BigDecimal totalCurrentLiabilities){
			this.totalCurrentLiabilities = totalCurrentLiabilities;
			return this;
		}
		
		public Builder currentAccountsPayable(BigDecimal currentAccountsPayable){
			this.currentAccountsPayable = currentAccountsPayable;
			return this;
		}
		
		public Builder deferredRevenue(BigDecimal deferredRevenue){
			this.deferredRevenue = deferredRevenue;
			return this;
		}
		
		public Builder currentDebt(BigDecimal currentDebt){
			this.currentDebt = currentDebt;
			return this;
		}
		
		public Builder shortTermDebt(BigDecimal shortTermDebt){
			this.shortTermDebt = shortTermDebt;
			return this;
		}
		
		public Builder totalNonCurrentLiabilities(BigDecimal totalNonCurrentLiabilities){
			this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
			return this;
		}
		
		public Builder capitalLeaseObligations(BigDecimal capitalLeaseObligations){
			this.capitalLeaseObligations = capitalLeaseObligations;
			return this;
		}
		
		public Builder longTermDebt(BigDecimal longTermDebt){
			this.longTermDebt = longTermDebt;
			return this;
		}
		
		public Builder currentLongTermDebt(BigDecimal currentLongTermDebt){
			this.currentLongTermDebt = currentLongTermDebt;
			return this;
		}
		
		public Builder longTermDebtNoncurrent(BigDecimal longTermDebtNoncurrent){
			this.longTermDebtNoncurrent = longTermDebtNoncurrent;
			return this;
		}
		
		public Builder shortLongTermDebtTotal(BigDecimal shortLongTermDebtTotal){
			this.shortLongTermDebtTotal = shortLongTermDebtTotal;
			return this;
		}
		
		public Builder otherCurrentLiabilities(BigDecimal otherCurrentLiabilities){
			this.otherCurrentLiabilities = otherCurrentLiabilities;
			return this;
		}
		
		public Builder otherNonCurrentLiabilities(BigDecimal otherNonCurrentLiabilities){
			this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
			return this;
		}
		
		public Builder totalShareholderEquity(BigDecimal totalShareholderEquity){
			this.totalShareholderEquity = totalShareholderEquity;
			return this;
		}
		
		public Builder treasuryStock(BigDecimal treasuryStock){
			this.treasuryStock = treasuryStock;
			return this;
		}
		
		public Builder retainedEarnings(BigDecimal retainedEarnings){
			this.retainedEarnings = retainedEarnings;
			return this;
		}
		
		public Builder commonStock(BigDecimal commonStock){
			this.commonStock = commonStock;
			return this;
		}
		public Builder commonStockSharesOutstanding(BigDecimal commonStockSharesOutstanding){
			this.commonStockSharesOutstanding = commonStockSharesOutstanding;
			return this;
		}
		
		public BalanceSheetReport build() {
			return new BalanceSheetReport(this);
		}
	}
}
