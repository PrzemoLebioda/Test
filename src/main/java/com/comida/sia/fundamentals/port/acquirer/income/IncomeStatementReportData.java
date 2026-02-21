package com.comida.sia.fundamentals.port.acquirer.income;

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.sync.supervision.domain.model.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeStatementReportData extends ReportData{
	private String tickerSymbol;
	private String reportedCurrency;
	private String grossProfit;
	private String totalRevenue;
	private String costOfRevenue;
	private String costofGoodsAndServicesSold;
	private String operatingIncome;
	private String sellingGeneralAndAdministrative;
	private String researchAndDevelopment;
	private String operatingExpenses;
	private String investmentIncomeNet;
	private String netInterestIncome;
	private String interestIncome;
	private String interestExpense;
	private String nonInterestIncome;
	private String otherNonOperatingIncome;
	private String depreciation;
	private String depreciationAndAmortization;
	private String incomeBeforeTax;
	private String incomeTaxExpense;
	private String interestAndDebtExpense;
	private String netIncomeFromContinuingOperations;
	private String comprehensiveIncomeNetOfTax;
	private String ebit;
	private String ebitda;
	private String netIncome;
	
	public IncomeStatementReportData() {
		super(Direction.ASCENDING);
	}

	@Override
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
		
	}

	@Override
	public String toString() {
		return "IncomeStatementReportData [tickerSymbol=" + tickerSymbol + ", reportedCurrency=" + reportedCurrency
				+ ", grossProfit=" + grossProfit + ", totalRevenue=" + totalRevenue + ", costOfRevenue=" + costOfRevenue
				+ ", costofGoodsAndServicesSold=" + costofGoodsAndServicesSold + ", operatingIncome=" + operatingIncome
				+ ", sellingGeneralAndAdministrative=" + sellingGeneralAndAdministrative + ", researchAndDevelopment="
				+ researchAndDevelopment + ", operatingExpenses=" + operatingExpenses + ", investmentIncomeNet="
				+ investmentIncomeNet + ", netInterestIncome=" + netInterestIncome + ", interestIncome="
				+ interestIncome + ", interestExpense=" + interestExpense + ", nonInterestIncome=" + nonInterestIncome
				+ ", otherNonOperatingIncome=" + otherNonOperatingIncome + ", depreciation=" + depreciation
				+ ", depreciationAndAmortization=" + depreciationAndAmortization + ", incomeBeforeTax="
				+ incomeBeforeTax + ", incomeTaxExpense=" + incomeTaxExpense + ", interestAndDebtExpense="
				+ interestAndDebtExpense + ", netIncomeFromContinuingOperations=" + netIncomeFromContinuingOperations
				+ ", comprehensiveIncomeNetOfTax=" + comprehensiveIncomeNetOfTax + ", ebit=" + ebit + ", ebitda="
				+ ebitda + ", netIncome=" + netIncome + ", fiscalDateEnding=" + fiscalDateEnding + "]";
	}
	
}
