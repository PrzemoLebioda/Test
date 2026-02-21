package com.comida.sia.fundamentals.domain.model.balancesheet;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetReportData;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class BalanceSheetQuarterReportTranslator implements ModelTranslator<BalanceSheetReportData, BalanceSheetReport>{

	@Override
	public BalanceSheetReport translate(BalanceSheetReportData source) throws ParseException {
		return new BalanceSheetReport.Builder(UUID.randomUUID(), source.getTickerSymbol(), ReportType.QUARTER_REPORT)
				.fiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.reportedCurrency(CurrencySymbol.get(source.getReportedCurrency()))
				.totalAssets(TranslationConcern.getNumberFrom(source.getTotalAssets()))
				.totalCurrentAssets(TranslationConcern.getNumberFrom(source.getTotalCurrentAssets()))
				.cashAndCashEquivalentsAtCarryingValue(TranslationConcern.getNumberFrom(source.getCashAndCashEquivalentsAtCarryingValue()))
				.cashAndShortTermInvestments(TranslationConcern.getNumberFrom(source.getCashAndShortTermInvestments()))
				.inventory(TranslationConcern.getNumberFrom(source.getInventory()))
				.currentNetReceivables(TranslationConcern.getNumberFrom(source.getCurrentNetReceivables()))
				.totalNonCurrentAssets(TranslationConcern.getNumberFrom(source.getTotalNonCurrentAssets()))
				.propertyPlantEquipment(TranslationConcern.getNumberFrom(source.getPropertyPlantEquipment()))
				.accumulatedDepreciationAmortizationPPE(TranslationConcern.getNumberFrom(source.getAccumulatedDepreciationAmortizationPPE()))
				.intangibleAssets(TranslationConcern.getNumberFrom(source.getIntangibleAssets()))
				.intangibleAssetsExcludingGoodwill(TranslationConcern.getNumberFrom(source.getIntangibleAssetsExcludingGoodwill()))
				.goodwill(TranslationConcern.getNumberFrom(source.getGoodwill()))
				.investments(TranslationConcern.getNumberFrom(source.getInvestments()))
				.longTermInvestments(TranslationConcern.getNumberFrom(source.getLongTermInvestments()))
				.shortTermInvestments(TranslationConcern.getNumberFrom(source.getShortTermInvestments()))
				.otherCurrentAssets(TranslationConcern.getNumberFrom(source.getOtherCurrentAssets()))
				.otherNonCurrentAssets(TranslationConcern.getNumberFrom(source.getOtherNonCurrentAssets()))
				.totalLiabilities(TranslationConcern.getNumberFrom(source.getTotalLiabilities()))
				.totalCurrentLiabilities(TranslationConcern.getNumberFrom(source.getTotalCurrentLiabilities()))
				.currentAccountsPayable(TranslationConcern.getNumberFrom(source.getCurrentAccountsPayable()))
				.deferredRevenue(TranslationConcern.getNumberFrom(source.getDeferredRevenue()))
				.currentDebt(TranslationConcern.getNumberFrom(source.getCurrentDebt()))
				.shortTermDebt(TranslationConcern.getNumberFrom(source.getShortTermDebt()))
				.totalNonCurrentLiabilities(TranslationConcern.getNumberFrom(source.getTotalNonCurrentLiabilities()))
				.capitalLeaseObligations(TranslationConcern.getNumberFrom(source.getCapitalLeaseObligations()))
				.longTermDebt(TranslationConcern.getNumberFrom(source.getLongTermDebt()))
				.currentLongTermDebt(TranslationConcern.getNumberFrom(source.getCurrentLongTermDebt()))
				.longTermDebtNoncurrent(TranslationConcern.getNumberFrom(source.getLongTermDebtNoncurrent()))
				.shortLongTermDebtTotal(TranslationConcern.getNumberFrom(source.getShortLongTermDebtTotal()))
				.otherCurrentLiabilities(TranslationConcern.getNumberFrom(source.getOtherCurrentLiabilities()))
				.otherNonCurrentLiabilities(TranslationConcern.getNumberFrom(source.getOtherNonCurrentLiabilities()))
				.totalShareholderEquity(TranslationConcern.getNumberFrom(source.getTotalShareholderEquity()))
				.treasuryStock(TranslationConcern.getNumberFrom(source.getTreasuryStock()))
				.retainedEarnings(TranslationConcern.getNumberFrom(source.getRetainedEarnings()))
				.commonStock(TranslationConcern.getNumberFrom(source.getCommonStock()))
				.commonStockSharesOutstanding(TranslationConcern.getNumberFrom(source.getCommonStockSharesOutstanding()))
				.build();
	}

}
