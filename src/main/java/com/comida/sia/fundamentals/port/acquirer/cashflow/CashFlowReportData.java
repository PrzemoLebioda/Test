package com.comida.sia.fundamentals.port.acquirer.cashflow;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.sync.supervision.domain.model.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashFlowReportData extends ReportData{
	private String tickerSymbol;
	private String reportedCurrency;
	private String operatingCashflow;
	private String paymentsForOperatingActivities;
	private String proceedsFromOperatingActivities;
	private String changeInOperatingLiabilities;
	private String changeInOperatingAssets;
	private String depreciationDepletionAndAmortization;
	private String capitalExpenditures;
	private String changeInReceivables;
	private String changeInInventory;
	private String profitLoss;
	private String cashflowFromInvestment;
	private String cashflowFromFinancing;
	private String proceedsFromRepaymentsOfShortTermDebt;
	private String paymentsForRepurchaseOfCommonStock;
	private String paymentsForRepurchaseOfEquity;
	private String paymentsForRepurchaseOfPreferredStock;
	private String dividendPayout;
	private String dividendPayoutCommonStock;
	private String dividendPayoutPreferredStock;
	private String proceedsFromIssuanceOfCommonStock;
	private String proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
	private String proceedsFromIssuanceOfPreferredStock;
	private String proceedsFromRepurchaseOfEquity;
	private String proceedsFromSaleOfTreasuryStock;
	private String changeInCashAndCashEquivalents;
	private String changeInExchangeRate;
	private String netIncome;
	
	public CashFlowReportData() {
		super(Direction.ASCENDING);
	}
	
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}
	
	@Override
	public String calculateLevel() {
		return super.calculateLevel();
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return super.occuranceTime();
	}

	@Override
	public String toString() {
		return "CashFlowReportData [tickerSymbol=" + tickerSymbol + ", reportedCurrency=" + reportedCurrency
				+ ", operatingCashflow=" + operatingCashflow + ", paymentsForOperatingActivities="
				+ paymentsForOperatingActivities + ", proceedsFromOperatingActivities="
				+ proceedsFromOperatingActivities + ", changeInOperatingLiabilities=" + changeInOperatingLiabilities
				+ ", changeInOperatingAssets=" + changeInOperatingAssets + ", depreciationDepletionAndAmortization="
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
				+ ", changeInExchangeRate=" + changeInExchangeRate + ", netIncome=" + netIncome + ", fiscalDateEnding="
				+ fiscalDateEnding + "]";
	}
}
