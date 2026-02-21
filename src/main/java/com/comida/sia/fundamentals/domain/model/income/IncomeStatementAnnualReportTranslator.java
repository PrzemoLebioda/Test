package com.comida.sia.fundamentals.domain.model.income;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementReportData;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class IncomeStatementAnnualReportTranslator implements ModelTranslator<IncomeStatementReportData, IncomeStatementReport> {

	@Override
	public IncomeStatementReport translate(IncomeStatementReportData source) throws ParseException {
		return new IncomeStatementReport.Builder(UUID.randomUUID(), source.getTickerSymbol(), ReportType.ANNUAL_REPORT)
				.fiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.reportedCurrency(CurrencySymbol.get(source.getReportedCurrency()))
				.grossProfit(TranslationConcern.getNumberFrom(source.getGrossProfit()))
				.totalRevenue(TranslationConcern.getNumberFrom(source.getTotalRevenue()))
				.costOfRevenue(TranslationConcern.getNumberFrom(source.getCostOfRevenue()))
				.costOfGoodsAndServicesSold(TranslationConcern.getNumberFrom(source.getCostofGoodsAndServicesSold()))
				.operatingIncome(TranslationConcern.getNumberFrom(source.getOperatingIncome()))
				.sellingGeneralAndAdministrative(TranslationConcern.getNumberFrom(source.getSellingGeneralAndAdministrative()))
				.researchAndDevelopment(TranslationConcern.getNumberFrom(source.getResearchAndDevelopment()))
				.operatingExpenses(TranslationConcern.getNumberFrom(source.getOperatingExpenses()))
				.investmentIncomeNet(TranslationConcern.getNumberFrom(source.getInvestmentIncomeNet()))
				.netInterestIncome(TranslationConcern.getNumberFrom(source.getNetInterestIncome()))
				.interestIncome(TranslationConcern.getNumberFrom(source.getInterestIncome()))
				.interestExpense(TranslationConcern.getNumberFrom(source.getInterestExpense()))
				.nonInterestIncome(TranslationConcern.getNumberFrom(source.getNonInterestIncome()))
				.otherNonOperatingIncome(TranslationConcern.getNumberFrom(source.getOtherNonOperatingIncome()))
				.depreciation(TranslationConcern.getNumberFrom(source.getDepreciation()))
				.depreciationAndAmortization(TranslationConcern.getNumberFrom(source.getDepreciationAndAmortization()))
				.incomeBeforeTax(TranslationConcern.getNumberFrom(source.getIncomeBeforeTax()))
				.incomeTaxExpense(TranslationConcern.getNumberFrom(source.getIncomeTaxExpense()))
				.interestAndDebtExpense(TranslationConcern.getNumberFrom(source.getInterestAndDebtExpense()))
				.netIncomeFromContinuingOperations(TranslationConcern.getNumberFrom(source.getNetIncomeFromContinuingOperations()))
				.comprehensiveIncomeNetOfTax(TranslationConcern.getNumberFrom(source.getComprehensiveIncomeNetOfTax()))
				.ebit(TranslationConcern.getNumberFrom(source.getEbit()))
				.ebitda(TranslationConcern.getNumberFrom(source.getEbitda()))
				.netIncome(TranslationConcern.getNumberFrom(source.getNetIncome()))
				.build();
	}

}
