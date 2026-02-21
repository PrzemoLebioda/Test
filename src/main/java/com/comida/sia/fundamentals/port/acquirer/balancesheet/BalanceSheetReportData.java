package com.comida.sia.fundamentals.port.acquirer.balancesheet;

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.sync.supervision.domain.model.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceSheetReportData extends ReportData {
	private String tickerSymbol;
	private String reportedCurrency;
	private String totalAssets;
	private String totalCurrentAssets;
	private String cashAndCashEquivalentsAtCarryingValue;
	private String cashAndShortTermInvestments;
	private String inventory;
	private String currentNetReceivables;
	private String totalNonCurrentAssets;
	private String propertyPlantEquipment;
	private String accumulatedDepreciationAmortizationPPE;
	private String intangibleAssets;
	private String intangibleAssetsExcludingGoodwill;
	private String goodwill;
	private String investments;
	private String longTermInvestments;
	private String shortTermInvestments;
	private String otherCurrentAssets;
	private String otherNonCurrentAssets;
	private String totalLiabilities;
	private String totalCurrentLiabilities;
	private String currentAccountsPayable;
	private String deferredRevenue;
	private String currentDebt;
	private String shortTermDebt;
	private String totalNonCurrentLiabilities;
	private String capitalLeaseObligations;
	private String longTermDebt;
	private String currentLongTermDebt;
	private String longTermDebtNoncurrent;
	private String shortLongTermDebtTotal;
	private String otherCurrentLiabilities;
	private String otherNonCurrentLiabilities;
	private String totalShareholderEquity;
	private String treasuryStock;
	private String retainedEarnings;
	private String commonStock;
	private String commonStockSharesOutstanding;
	
	public BalanceSheetReportData() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}
	
	@Override
	public String toString() {
		return "BalanceSheetReportData [fiscalDateEnding=" + fiscalDateEnding + ", reportedCurrency=" + reportedCurrency
				+ ", totalAssets=" + totalAssets + ", totalCurrentAssets=" + totalCurrentAssets
				+ ", cashAndCashEquivalentsAtCarryingValue=" + cashAndCashEquivalentsAtCarryingValue
				+ ", cashAndShortTermInvestments=" + cashAndShortTermInvestments + ", inventory=" + inventory
				+ ", currentNetReceivables=" + currentNetReceivables + ", totalNonCurrentAssets="
				+ totalNonCurrentAssets + ", propertyPlantEquipment=" + propertyPlantEquipment
				+ ", accumulatedDepreciationAmortizationPPE=" + accumulatedDepreciationAmortizationPPE
				+ ", intangibleAssets=" + intangibleAssets + ", intangibleAssetsExcludingGoodwill="
				+ intangibleAssetsExcludingGoodwill + ", goodwill=" + goodwill + ", investments=" + investments
				+ ", longTermInvestments=" + longTermInvestments + ", shortTermInvestments=" + shortTermInvestments
				+ ", otherCurrentAssets=" + otherCurrentAssets + ", otherNonCurrentAssets=" + otherNonCurrentAssets
				+ ", totalLiabilities=" + totalLiabilities + ", totalCurrentLiabilities=" + totalCurrentLiabilities
				+ ", currentAccountsPayable=" + currentAccountsPayable + ", deferredRevenue=" + deferredRevenue
				+ ", currentDebt=" + currentDebt + ", shortTermDebt=" + shortTermDebt + ", totalNonCurrentLiabilities="
				+ totalNonCurrentLiabilities + ", capitalLeaseObligations=" + capitalLeaseObligations
				+ ", longTermDebt=" + longTermDebt + ", currentLongTermDebt=" + currentLongTermDebt
				+ ", longTermDebtNoncurrent=" + longTermDebtNoncurrent + ", shortLongTermDebtTotal="
				+ shortLongTermDebtTotal + ", otherCurrentLiabilities=" + otherCurrentLiabilities
				+ ", otherNonCurrentLiabilities=" + otherNonCurrentLiabilities + ", totalShareholderEquity="
				+ totalShareholderEquity + ", treasuryStock=" + treasuryStock + ", retainedEarnings=" + retainedEarnings
				+ ", commonStock=" + commonStock + ", commonStockSharesOutstanding=" + commonStockSharesOutstanding
				+ "]";
	}

}
