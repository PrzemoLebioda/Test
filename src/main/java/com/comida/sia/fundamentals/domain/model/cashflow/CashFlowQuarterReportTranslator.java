package com.comida.sia.fundamentals.domain.model.cashflow;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowReportData;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class CashFlowQuarterReportTranslator implements ModelTranslator<CashFlowReportData, CashFlowReport>{

	@Override
	public CashFlowReport translate(CashFlowReportData source) throws ParseException {
		
		return new CashFlowReport.Builder(UUID.randomUUID(), source.getTickerSymbol(), ReportType.QUARTER_REPORT)
				.fiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.reportedCurrency(CurrencySymbol.get(source.getReportedCurrency()))
				.operatingCashflow(TranslationConcern.getNumberFrom(source.getOperatingCashflow()))
				.paymentsForOperatingActivities(TranslationConcern.getNumberFrom(source.getPaymentsForOperatingActivities()))
				.proceedsFromOperatingActivities(TranslationConcern.getNumberFrom(source.getProceedsFromOperatingActivities()))
				.changeInOperatingLiabilities(TranslationConcern.getNumberFrom(source.getChangeInOperatingLiabilities()))
				.changeInOperatingAssets(TranslationConcern.getNumberFrom(source.getChangeInOperatingAssets()))
				.depreciationDepletionAndAmortization(TranslationConcern.getNumberFrom(source.getDepreciationDepletionAndAmortization()))
				.capitalExpenditures(TranslationConcern.getNumberFrom(source.getCapitalExpenditures()))
				.changeInReceivables(TranslationConcern.getNumberFrom(source.getChangeInReceivables()))
				.changeInInventory(TranslationConcern.getNumberFrom(source.getChangeInInventory()))
				.profitLoss(TranslationConcern.getNumberFrom(source.getProfitLoss()))
				.cashflowFromInvestment(TranslationConcern.getNumberFrom(source.getCashflowFromInvestment()))
				.cashflowFromFinancing(TranslationConcern.getNumberFrom(source.getCashflowFromFinancing()))
				.proceedsFromRepaymentsOfShortTermDebt(TranslationConcern.getNumberFrom(source.getProceedsFromRepaymentsOfShortTermDebt()))
				.paymentsForRepurchaseOfCommonStock(TranslationConcern.getNumberFrom(source.getPaymentsForRepurchaseOfCommonStock()))
				.paymentsForRepurchaseOfEquity(TranslationConcern.getNumberFrom(source.getPaymentsForRepurchaseOfEquity()))
				.paymentsForRepurchaseOfPreferredStock(TranslationConcern.getNumberFrom(null))
				.dividendPayout(TranslationConcern.getNumberFrom(source.getDividendPayout()))
				.dividendPayoutCommonStock(TranslationConcern.getNumberFrom(source.getDividendPayoutCommonStock()))
				.dividendPayoutPreferredStock(TranslationConcern.getNumberFrom(source.getDividendPayoutPreferredStock()))
				.proceedsFromIssuanceOfCommonStock(TranslationConcern.getNumberFrom(source.getProceedsFromIssuanceOfCommonStock()))
				.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet(TranslationConcern.getNumberFrom(source.getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet()))
				.proceedsFromIssuanceOfPreferredStock(TranslationConcern.getNumberFrom(source.getProceedsFromIssuanceOfPreferredStock()))
				.proceedsFromRepurchaseOfEquity(TranslationConcern.getNumberFrom(source.getProceedsFromRepurchaseOfEquity()))
				.proceedsFromSaleOfTreasuryStock(TranslationConcern.getNumberFrom(source.getProceedsFromSaleOfTreasuryStock()))
				.changeInCashAndCashEquivalents(TranslationConcern.getNumberFrom(source.getChangeInCashAndCashEquivalents()))
				.changeInExchangeRate(TranslationConcern.getNumberFrom(source.getChangeInExchangeRate()))
				.netIncome(TranslationConcern.getNumberFrom(source.getNetIncome()))
				.build();
		
	}

}